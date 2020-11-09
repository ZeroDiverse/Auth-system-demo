package com.zerod.authdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponse {
    //Id of the login
    private Long id;
    //Email
    private String email;
    //Username
    private String username;
    //Access token (JWT)
    private String accessToken;
    //Expiration date
    private Instant expiredAt;
    //Refresh Token
    private String refreshToken;
}
