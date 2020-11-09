package com.zerod.authdemo.controllers;

import com.zerod.authdemo.dto.*;
import com.zerod.authdemo.exceptions.RefreshTokenException;
import com.zerod.authdemo.exceptions.UnknownVerificationTokenException;
import com.zerod.authdemo.services.AuthService;
import com.zerod.authdemo.services.RefreshTokenService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Auth controller for app
 */
@RestController
@RequestMapping("/auth")
@AllArgsConstructor
@Slf4j
public class AuthController {
    //Auth service to handle layer
    private final AuthService authService;

    private final RefreshTokenService refreshTokenService;

    /**
     * Login route
     *
     * @param signupRequest the request body for signup
     * @return the login response in rest api
     */
    @PostMapping("/signup")
    public @ResponseBody
    ResponseEntity<SignupResponse> signup(@RequestBody SignupRequest signupRequest) {
        //If success return a http status of created ( 201 ) and the login response from auth service
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.signup(signupRequest));
    }

    @GetMapping("/verify/{token}")
    public @ResponseBody
    ResponseEntity<SignupResponse> verifySignupVerificationToken(@PathVariable String token) throws UnknownVerificationTokenException {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.verificationSignup(token));
    }
    /**
     * Login route
     *
     * @param loginRequest the request body for login
     * @return the login response in rest api
     */
    @PostMapping("/login")
    public @ResponseBody
    ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        //If success return a http status of created ( 201 ) and the login response from auth service
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.login(loginRequest));
    }


    /**
     * Refresh token
     *
     * @param refreshTokenRequest the request body for refresh token
     * @return the login response in rest api
     */
    @PostMapping("/refresh/token")
    public @ResponseBody
    ResponseEntity<LoginResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) throws RefreshTokenException {
        return ResponseEntity.status(HttpStatus.OK).body(authService.refreshToken(refreshTokenRequest));
    }

    /**
     * Logout
     *
     * @param refreshTokenRequest the request body for refresh token
     * @return the login response in rest api
     */
    @DeleteMapping("/logout")
    public @ResponseBody
    ResponseEntity<String> deleteRefreshToken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.deleteRefreshToken(refreshTokenRequest.getToken());
        return ResponseEntity.status(HttpStatus.OK).body("Refresh token deleted");
    }

}
