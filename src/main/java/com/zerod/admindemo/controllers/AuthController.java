package com.zerod.admindemo.controllers;

import com.zerod.admindemo.dto.LoginRequest;
import com.zerod.admindemo.dto.LoginResponse;
import com.zerod.admindemo.services.AuthService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
@Slf4j
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public @ResponseBody
    ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.login(loginRequest));
    }
}
