package com.zerod.authdemo.services;

import com.zerod.authdemo.configs.JwtConfig;
import com.zerod.authdemo.dto.*;
import com.zerod.authdemo.exceptions.RefreshTokenException;
import com.zerod.authdemo.exceptions.UnknownVerificationTokenException;
import com.zerod.authdemo.models.*;
import com.zerod.authdemo.repositories.UserRepository;
import com.zerod.authdemo.repositories.VerificationTokenRepository;
import com.zerod.authdemo.security.PasswordConfig;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.UUID;

/**
 * Auth service for auth route
 */
@Service
@AllArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final JwtTokenService jwtTokenService;
    private final JwtConfig jwtConfig;
    private final RefreshTokenService refreshTokenService;
    private final MailService mailService;
    private final PasswordConfig passwordConfig;

    /**
     * Sign up for auth
     * @param signupRequest signup request
     * @return the signup response
     */
    @Transactional
    public SignupResponse signup(SignupRequest signupRequest) {
        //Save user to the database
        User user = userRepository.save(
                User.builder()
                        .email(signupRequest.getEmail())
                        .password(passwordConfig.passwordEncoder().encode(signupRequest.getPassword()))
                        .enabled(false)
                        .createdAt(Instant.now())
                        .role(Role.LV3)
                        .build());

        //Create verification token for signup
        String token = UUID.randomUUID().toString();

        //Save verification token to the database
        verificationTokenRepository.save(VerificationToken
                .builder()
                .token(token)
                //.expiredAt(Instant.now().plus(3, ChronoUnit.DAYS))
                .expiredAt(Instant.now().plus(3, ChronoUnit.DAYS))
                .user(user)
                .build());

        //Create a mail to send to the user
        Mail mail = Mail.builder()
                .mailType(MailType.SEND)
                .client(signupRequest.getEmail())
                .body("http://localhost:8080/auth/verify/" + token)
                .subject("Registration mail!")
                .build();

        //Send the mail
        mailService.sendMimeMessage(mail);

        //Send the response back to the client
        return SignupResponse.builder().message("Account created! You will receive email soon").build();
    }

    /**
     * Verification the token
     * @param token verification token pass in as param
     * @return the Response to the client
     * @throws UnknownVerificationTokenException exception if token not found
     */
    @Transactional
    public SignupResponse verificationSignup(String token) throws UnknownVerificationTokenException {
        //Get the verification token from database
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token).orElseThrow(() -> new UnknownVerificationTokenException("Unknown verification token"));

        if(verificationToken.getExpiredAt().isBefore(Instant.now())){
            throw new UnknownVerificationTokenException("Expired verification token");
        }

        User user = verificationToken.getUser();

        user.setEnabled(true);

        userRepository.save(user);

        return SignupResponse.builder().message("User enabled!").build();
    }

    /**
     * Login service
     *
     * @param loginRequest the login json request
     * @return the login response after checking authentication
     */
    @Transactional
    public LoginResponse login(LoginRequest loginRequest) {
        //Authenticate and generate authentication object
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        //Set authentication object to security context holder
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //Get user by email
        User user = userRepository.findByEmail(loginRequest.getEmail()).orElse(new User());
        //Return the login response object
        return LoginResponse.builder()
                .id(user.getUserId())
                .email(user.getEmail())
                .expiredAt(Instant.now().plusMillis(jwtConfig.getSecretExpiration()))
                .refreshToken(refreshTokenService.generateRefreshToken().getToken())
                .username(user.getUsername())
                .accessToken(jwtTokenService.generateToken(authentication))
                .build();
    }

    public User getCurrentUser() {
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByEmail(user.getUsername()).orElseThrow(() -> new UsernameNotFoundException("There is no user with this email"));
    }

    public boolean isLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
    }

    @Transactional
    public LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest) throws RefreshTokenException {
        refreshTokenService.validateRefreshToken(refreshTokenRequest.getToken());
        Optional<User> user = userRepository.findByEmail(refreshTokenRequest.getEmail());
        String token = jwtTokenService.generateTokenWithCredential(refreshTokenRequest.getEmail(), user.orElse(new User()).getRole().getGrantedAuthorities());
        return LoginResponse.builder()
                .refreshToken(refreshTokenRequest.getToken())
                .email(refreshTokenRequest.getEmail())
                .expiredAt(Instant.now().plusMillis(jwtConfig.getSecretExpiration()))
                .refreshToken(refreshTokenService.generateRefreshToken().getToken())
                .accessToken(token)
                .build();
    }
}
