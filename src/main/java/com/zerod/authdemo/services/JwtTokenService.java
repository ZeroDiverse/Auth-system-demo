package com.zerod.authdemo.services;

import com.zerod.authdemo.configs.JwtConfig;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.util.Collection;
import java.util.Date;

/**
 * Jwt Token Service to generate jwt access token
 */
@Service
@AllArgsConstructor
public class JwtTokenService {
    // We need a signing key, so we'll create one just for this example. Usually
// the key would be read from your application configuration instead.
    public static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    //DI JWT Config to get configuration
    private final JwtConfig jwtConfigs;

    /**
     * Generate access token (jwt)
     *
     * @param authentication authentication object
     * @return jwt access token
     */
    public String generateToken(Authentication authentication) {
        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim("roles", authentication.getAuthorities())
                .setIssuedAt(new Date())
                //.setExpiration(java.sql.Date.valueOf(LocalDate.now().plusWeeks(jwtConfigs.getSecretExpiration())))
                .setExpiration(Date.from(Instant.now().plusMillis(jwtConfigs.getSecretExpiration())))
                .signWith(key).compact();
    }

    /**
     * Generate token with existing credential (for refresh token)
     *
     * @param credential  the credential that come int
     * @param authorities authorities of the credential
     * @return the new access token for refresh token
     */
    public String generateTokenWithCredential(String credential, Collection<? extends GrantedAuthority> authorities) {
        return Jwts.builder()
                .setSubject(credential)
                .claim("roles", authorities)
                .setIssuedAt(new Date())
                //.setExpiration(java.sql.Date.valueOf(LocalDate.now().plusWeeks(jwtConfigs.getSecretExpiration())))
                .setExpiration(Date.from(Instant.now().plusMillis(jwtConfigs.getSecretExpiration())))
                .signWith(key).compact();
    }
}
