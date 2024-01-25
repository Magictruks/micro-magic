package com.authentication_service.module.authentication.application.controllers;

import com.authentication_service.module.authentication.application.facades.AuthApplicationToJwt;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthPublicKeyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthApplicationToJwt authApplicationToJwt;

    @Test
    void getPublicKey_ReturnsPublicKey() throws Exception {
        String publicKey = "test-public-key";
        given(authApplicationToJwt.getPublicKeyForJwt()).willReturn(publicKey);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/auth/public-key"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.TEXT_PLAIN + ";charset=UTF-8"))
                .andExpect(content().string(publicKey));
    }
}