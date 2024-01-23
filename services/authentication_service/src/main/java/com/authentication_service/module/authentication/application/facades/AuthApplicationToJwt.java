package com.authentication_service.module.authentication.application.facades;

import com.authentication_jwt.jwt.services.JwtKeyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class AuthApplicationToJwt {
    private final JwtKeyService jwtKeyService;

    public String getPublicKeyForJwt() throws IOException {
        return jwtKeyService.getPublicKeyString();
    }
}
