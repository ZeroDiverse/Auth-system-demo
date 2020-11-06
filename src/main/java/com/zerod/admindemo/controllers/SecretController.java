package com.zerod.admindemo.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secret")
@AllArgsConstructor
@Slf4j
public class SecretController {
    @GetMapping
    public ResponseEntity<String> getSecret() {
        return ResponseEntity.status(HttpStatus.OK).body("Hello");
    }
}
