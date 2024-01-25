package com.authentication_service.module.authentication.application.facades;

import com.authentication_service.module.authentication.application.mappers.AuthApplicationMapper;
import com.authentication_service.module.authentication.application.models.RegisterRequest;
import com.authentication_service.module.authentication.application.models.TokenApplication;
import com.authentication_service.module.authentication.domain.interfaces.AuthService;
import com.authentication_service.module.authentication.domain.models.AuthRegisterDomain;
import com.authentication_service.module.authentication.domain.models.TokenDomain;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
class AuthApplicationToDomainFacadeTest {
    @Mock
    private AuthService authService;

    @Mock
    private AuthApplicationMapper authApplicationMapper;

    @InjectMocks
    private AuthApplicationToDomainFacade authApplicationToDomainFacade;

    @Test
    void loginUserByEmailAndPassword_ReturnsToken() throws GeneralSecurityException, IOException {
        String email = "test@example.com";
        String password = "password123";
        Optional<TokenDomain> tokenDomain = Optional.of(new TokenDomain()); // Replace with actual TokenDomain object
        Optional<TokenApplication> tokenApplication = Optional.of(new TokenApplication()); // Replace with actual TokenApplication object

        when(authService.loginUserByEmailAndPassword(email, password)).thenReturn(tokenDomain);
        when(authApplicationMapper.tokenFromDomain(any(TokenDomain.class))).thenReturn(tokenApplication.get());

        Optional<TokenApplication> result = authApplicationToDomainFacade.loginUserByEmailAndPassword(email, password);

        assertTrue(result.isPresent());
        assertEquals(tokenApplication, result);
        verify(authService).loginUserByEmailAndPassword(email, password);
        verify(authApplicationMapper).tokenFromDomain(any(TokenDomain.class));
    }

    @Test
    void loginUserByEmailAndPassword_ReturnsEmptyOptional() throws GeneralSecurityException, IOException {
        String email = "nonexistent@example.com";
        String password = "wrongpassword";

        when(authService.loginUserByEmailAndPassword(email, password)).thenReturn(Optional.empty());

        Optional<TokenApplication> result = authApplicationToDomainFacade.loginUserByEmailAndPassword(email, password);

        assertFalse(result.isPresent());
        verify(authService).loginUserByEmailAndPassword(email, password);
        verify(authApplicationMapper, never()).tokenFromDomain(any(TokenDomain.class));
    }

    @Test
    void registerUser_ReturnsToken() throws GeneralSecurityException, IOException {
        RegisterRequest registerRequest = new RegisterRequest(); // Set up RegisterRequest object
        AuthRegisterDomain authRegisterDomain = new AuthRegisterDomain(); // Replace with actual AuthRegisterDomain object
        Optional<TokenDomain> tokenDomain = Optional.of(new TokenDomain()); // Replace with actual TokenDomain object
        Optional<TokenApplication> tokenApplication = Optional.of(new TokenApplication()); // Replace with actual TokenApplication object

        when(authApplicationMapper.registerToDomain(registerRequest)).thenReturn(authRegisterDomain);
        when(authService.registerUser(authRegisterDomain)).thenReturn(tokenDomain);
        when(authApplicationMapper.tokenFromDomain(any(TokenDomain.class))).thenReturn(tokenApplication.get());

        Optional<TokenApplication> result = authApplicationToDomainFacade.registerUser(registerRequest);

        assertTrue(result.isPresent());
        assertEquals(tokenApplication, result);
        verify(authApplicationMapper).registerToDomain(registerRequest);
        verify(authService).registerUser(authRegisterDomain);
        verify(authApplicationMapper).tokenFromDomain(any(TokenDomain.class));
    }

    @Test
    void registerUser_ReturnsEmptyOptional() throws GeneralSecurityException, IOException {
        RegisterRequest registerRequest = new RegisterRequest(); // Set up RegisterRequest object
        AuthRegisterDomain authRegisterDomain = new AuthRegisterDomain(); // Replace with actual AuthRegisterDomain object

        when(authApplicationMapper.registerToDomain(registerRequest)).thenReturn(authRegisterDomain);
        when(authService.registerUser(authRegisterDomain)).thenReturn(Optional.empty());

        Optional<TokenApplication> result = authApplicationToDomainFacade.registerUser(registerRequest);

        assertFalse(result.isPresent());
        verify(authApplicationMapper).registerToDomain(registerRequest);
        verify(authService).registerUser(authRegisterDomain);
        verify(authApplicationMapper, never()).tokenFromDomain(any(TokenDomain.class));
    }
}