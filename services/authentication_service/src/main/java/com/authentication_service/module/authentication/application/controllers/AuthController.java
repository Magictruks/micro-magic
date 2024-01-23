package com.authentication_service.module.authentication.application.controllers;

import com.authentication_service.module.authentication.application.facades.AuthApplicationToDomainFacade;
import com.authentication_service.module.authentication.application.models.LoginRequest;
import com.authentication_service.module.authentication.application.models.RegisterRequest;
import com.authentication_service.module.authentication.application.models.TokenApplication;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthApplicationToDomainFacade authApplicationToDomainFacade;

    @PostMapping("/login")
    public ResponseEntity<Optional<TokenApplication>> login(@RequestBody LoginRequest request) throws GeneralSecurityException, IOException {
        Optional<TokenApplication> tokenApplication = authApplicationToDomainFacade.loginUserByEmailAndPassword(request.getEmail(), request.getPassword());
        return new ResponseEntity<>(tokenApplication, tokenApplication.isEmpty() ? HttpStatus.UNAUTHORIZED : HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Optional<TokenApplication>> login(@RequestBody RegisterRequest request) throws GeneralSecurityException, IOException {
        Optional<TokenApplication> tokenApplication = authApplicationToDomainFacade.registerUser(request);
        return new ResponseEntity<>(tokenApplication, tokenApplication.isEmpty() ? HttpStatus.UNAUTHORIZED : HttpStatus.OK);
    }
}
