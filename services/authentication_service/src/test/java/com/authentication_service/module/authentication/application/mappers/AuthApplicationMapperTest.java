package com.authentication_service.module.authentication.application.mappers;

import com.authentication_service.module.authentication.application.models.RegisterRequest;
import com.authentication_service.module.authentication.application.models.TokenApplication;
import com.authentication_service.module.authentication.domain.models.AuthRegisterDomain;
import com.authentication_service.module.authentication.domain.models.TokenDomain;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
class AuthApplicationMapperTest {
    private final AuthApplicationMapper mapper = Mappers.getMapper(AuthApplicationMapper.class);

    @Test
    void tokenFromDomain_ShouldMapCorrectly() {
        TokenDomain tokenDomain = new TokenDomain();
        tokenDomain.setAccessToken("testToken"); // Assurez-vous de remplacer par les vrais champs
        // Ajouter d'autres champs si nécessaire

        TokenApplication tokenApplication = mapper.tokenFromDomain(tokenDomain);

        assertNotNull(tokenApplication);
        assertEquals(tokenDomain.getAccessToken(), tokenApplication.getAccessToken());
        // Vérifiez d'autres champs si nécessaire
    }

    @Test
    void registerToDomain_ShouldMapCorrectly() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("example@example.com");
        registerRequest.setPassword("password123"); // Assurez-vous de remplacer par les vrais champs
        // Ajouter d'autres champs si nécessaire

        AuthRegisterDomain authRegisterDomain = mapper.registerToDomain(registerRequest);

        assertNotNull(authRegisterDomain);
        assertEquals(registerRequest.getEmail(), authRegisterDomain.getEmail());
        assertEquals(registerRequest.getPassword(), authRegisterDomain.getPassword());
        // Vérifiez d'autres champs si nécessaire
    }
}