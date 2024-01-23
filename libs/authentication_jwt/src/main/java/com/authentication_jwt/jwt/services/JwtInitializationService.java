package com.authentication_jwt.jwt.services;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;

@RequiredArgsConstructor
@Service
@Slf4j
public class JwtInitializationService {
    private final JwtKeyService jwtKeyService;

    @PostConstruct
    public void initWithPublicKey() throws GeneralSecurityException, IOException {
        log.info("INIT PUBLIC KEY");
        jwtKeyService.getPublicKey();
    }
}
