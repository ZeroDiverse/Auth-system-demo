package com.zerod.admindemo.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Secret route
 */
@RestController
@RequestMapping("/api")
@AllArgsConstructor
@Slf4j
public class SecretController {
    @GetMapping
    public ResponseEntity<String> getSecret() {
        return ResponseEntity.status(HttpStatus.OK).body("Hello");
    }

    @GetMapping("/lv0/data")
    @PreAuthorize("hasAnyRole('ROLE_LV0')")
    public ResponseEntity<String> getSecretLV0() {
        return ResponseEntity.status(HttpStatus.OK).body("Hello lv0");
    }

    @GetMapping("/lv1/data")
    @PreAuthorize("hasAnyRole('ROLE_LV1')")
    public ResponseEntity<String> getSecretLV1() {
        return ResponseEntity.status(HttpStatus.OK).body("Hello lv1");
    }
}
