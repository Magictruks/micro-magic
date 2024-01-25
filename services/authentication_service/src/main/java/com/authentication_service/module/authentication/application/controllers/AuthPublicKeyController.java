package com.authentication_service.module.authentication.application.controllers;

import com.authentication_service.module.authentication.application.facades.AuthApplicationToJwt;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.GeneralSecurityException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthPublicKeyController {
    private final AuthApplicationToJwt authApplicationToJwt;
    @GetMapping("/public-key")
    public ResponseEntity<String> getPublicKey() throws IOException {
        String publicKey = authApplicationToJwt.getPublicKeyForJwt();
        return new ResponseEntity<>(publicKey, HttpStatus.OK);
    }
}
