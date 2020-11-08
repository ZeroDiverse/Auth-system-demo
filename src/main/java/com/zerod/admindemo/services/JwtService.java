package com.zerod.admindemo.services;

import com.zerod.admindemo.configs.JwtConfig;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.LocalDate;
import java.util.Date;

@Service
@AllArgsConstructor
public class JwtService {
    // We need a signing key, so we'll create one just for this example. Usually
// the key would be read from your application configuration instead.
    public static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    private final JwtConfig jwtConfigs;

    public String generateToken(Authentication authentication) {
        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim("roles", authentication.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusWeeks(jwtConfigs.getSecretExpiration())))
                .signWith(key).compact();
    }
}
