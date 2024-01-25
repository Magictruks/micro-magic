package com.authentication_service.module.authentication.application.controllers;

import com.authentication_service.module.authentication.application.facades.AuthApplicationToDomainFacade;
import com.authentication_service.module.authentication.application.models.LoginRequest;
import com.authentication_service.module.authentication.application.models.RegisterRequest;
import com.authentication_service.module.authentication.application.models.TokenApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@WebMvcTest(AuthController.class)
@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {
    @MockBean
    private AuthApplicationToDomainFacade authApplicationToDomainFacade;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }


    @Test
    void login_ReturnsToken_WhenCredentialsAreValid() throws Exception {
        LoginRequest loginRequest = new LoginRequest()
                .setEmail("user@example.com")
                .setPassword("password123");

        TokenApplication tokenApplication = new TokenApplication().setAccessToken("some-token");

        Optional<TokenApplication> oTokenApplication = Optional.of(tokenApplication);

        given(authApplicationToDomainFacade.loginUserByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword()))
                .willReturn(oTokenApplication);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").value(tokenApplication.getAccessToken()));
    }

    @Test
    void login_ReturnsUnauthorized_WhenCredentialsAreInvalid() throws Exception {
        LoginRequest loginRequest = new LoginRequest()
                .setEmail("user@example.com")
                .setPassword("password123");

        given(authApplicationToDomainFacade.loginUserByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword()))
                .willReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void register_ReturnsToken_WhenRegistrationIsSuccessful() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest()
                .setEmail("newuser@example.com")
                .setPassword("password123");

        TokenApplication tokenApplication = new TokenApplication().setAccessToken("some-token");

        Optional<TokenApplication> oTokenApplication = Optional.of(tokenApplication);

        given(authApplicationToDomainFacade.registerUser(registerRequest)).willReturn(oTokenApplication);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").value(tokenApplication.getAccessToken()));
    }

    @Test
    void register_ReturnsUnauthorized_WhenRegistrationFails() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest()
                .setEmail("newuser@example.com")
                .setPassword("password123");

        given(authApplicationToDomainFacade.registerUser(registerRequest)).willReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerRequest)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void login_whenEmailIsNotValid_thenValidationFails() {
        LoginRequest request = new LoginRequest()
                .setEmail("invalidemail")
                .setPassword("password123");

        Set<ConstraintViolation<LoginRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Invalid email format")));
    }

    @Test
    void login_whenPasswordIsBlank_thenValidationFails() {
        LoginRequest request = new LoginRequest()
                .setEmail("user@example.com")
                .setPassword("");

        Set<ConstraintViolation<LoginRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Password cannot be blank")));
    }

    @Test
    void login_whenRequestIsValid_thenValidationSucceeds() {
        LoginRequest request = new LoginRequest()
                .setEmail("user@example.com")
                .setPassword("password123");

        Set<ConstraintViolation<LoginRequest>> violations = validator.validate(request);

        assertTrue(violations.isEmpty());
    }

    @Test
    void register_whenEmailIsNotValid_thenValidationFails() {
        RegisterRequest request = new RegisterRequest()
                .setEmail("invalidemail")
                .setPassword("password123");

        Set<ConstraintViolation<RegisterRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Invalid email format")));
    }

    @Test
    void register_whenPasswordIsBlank_thenValidationFails() {
        RegisterRequest request = new RegisterRequest()
                .setEmail("user@example.com")
                .setPassword("");

        Set<ConstraintViolation<RegisterRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Password cannot be blank")));
    }

    @Test
    void register_whenRequestIsValid_thenValidationSucceeds() {
        RegisterRequest request = new RegisterRequest()
                .setEmail("user@example.com")
                .setPassword("password123");

        Set<ConstraintViolation<RegisterRequest>> violations = validator.validate(request);

        assertTrue(violations.isEmpty());
    }

}