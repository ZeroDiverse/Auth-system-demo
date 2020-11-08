package com.zerod.admindemo.configs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Jwt configuration
 */
@Component
@ConfigurationProperties(prefix = "application.jwt")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtConfig {
    //Secret key for jwt (NOT using right now)
    private String secretKey;
    //Jwt Expiration date
    private Integer secretExpiration;
}
