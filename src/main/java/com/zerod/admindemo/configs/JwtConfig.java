package com.zerod.admindemo.configs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "application.jwt")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtConfig {
    private String secretKey;
    private Integer secretExpiration;
}
