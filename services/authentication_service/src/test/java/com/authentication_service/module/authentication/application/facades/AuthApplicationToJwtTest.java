package com.authentication_service.module.authentication.application.facades;

import com.authentication_jwt.jwt.services.JwtKeyService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class AuthApplicationToJwtTest {
    @Mock
    private JwtKeyService jwtKeyService;

    @InjectMocks
    private AuthApplicationToJwt authApplicationToJwt;

    @Test
    void getPublicKeyForJwt_ReturnsPublicKeyString() throws IOException {
        String publicKeyString = "publicKeyString"; // Replace with actual public key string
        when(jwtKeyService.getPublicKeyString()).thenReturn(publicKeyString);

        String result = authApplicationToJwt.getPublicKeyForJwt();

        assertEquals(publicKeyString, result);
        verify(jwtKeyService).getPublicKeyString();
    }
}