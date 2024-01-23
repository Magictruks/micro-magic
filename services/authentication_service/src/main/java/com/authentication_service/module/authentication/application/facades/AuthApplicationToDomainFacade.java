package com.authentication_service.module.authentication.application.facades;

import com.authentication_service.module.authentication.application.mappers.AuthApplicationMapper;
import com.authentication_service.module.authentication.application.models.RegisterRequest;
import com.authentication_service.module.authentication.application.models.TokenApplication;
import com.authentication_service.module.authentication.domain.interfaces.AuthService;
import com.authentication_service.module.authentication.domain.models.AuthRegisterDomain;
import com.authentication_service.module.authentication.domain.models.TokenDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthApplicationToDomainFacade {
    private final AuthService authService;
    private final AuthApplicationMapper authApplicationMapper;
    public Optional<TokenApplication> loginUserByEmailAndPassword(String email, String password) throws GeneralSecurityException, IOException {
        Optional<TokenDomain> token = authService.loginUserByEmailAndPassword(email, password);
        return token.map(authApplicationMapper::tokenFromDomain);
    }
    public Optional<TokenApplication> registerUser(RegisterRequest registerRequest) throws GeneralSecurityException, IOException {
        AuthRegisterDomain authRegisterDomain = authApplicationMapper.registerToDomain(registerRequest);
        Optional<TokenDomain> token = authService.registerUser(authRegisterDomain);
        return token.map(authApplicationMapper::tokenFromDomain);
    }
}
