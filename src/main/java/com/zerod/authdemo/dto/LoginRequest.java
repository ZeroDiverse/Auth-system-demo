package com.zerod.authdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Login request for rest api
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    //Email to login
    private String email;
    //Password to login
    private String password;
}
