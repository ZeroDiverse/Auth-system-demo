package com.zerod.admindemo.controllers;

import com.zerod.admindemo.dto.LoginRequest;
import com.zerod.admindemo.dto.LoginResponse;
import com.zerod.admindemo.services.AuthService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


}
