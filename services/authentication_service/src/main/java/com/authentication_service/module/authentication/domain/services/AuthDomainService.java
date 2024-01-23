package com.authentication_service.module.authentication.domain.services;

import com.authentication_jwt.jwt.services.JwtGenerateService;
import com.authentication_service.module.authentication.domain.facades.AuthDomainToUserServiceFacade;
import com.authentication_service.module.authentication.domain.interfaces.AuthService;
import com.authentication_service.module.authentication.domain.mappers.AuthDomainMapper;
import com.authentication_service.module.authentication.domain.models.AuthRegisterDomain;
import com.authentication_service.module.authentication.domain.models.AuthUserDomain;
import com.authentication_service.module.authentication.domain.models.TokenDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthDomainService implements AuthService {
    private final AuthDomainMapper authDomainMapper;
    private final AuthDomainToUserServiceFacade userServiceFacade;
    private final JwtGenerateService jwtGenerateService;
    public Optional<TokenDomain> loginUserByEmailAndPassword(String email, String password) throws GeneralSecurityException, IOException {
        // check password
        boolean checked = userServiceFacade.checkPassword(email, password);

        if (!checked) {
            // wrong password error
            return Optional.empty();
        }

        Optional<AuthUserDomain> userByEmail = findUserByEmail(email);

        if (userByEmail.isEmpty()) {
            return Optional.empty();
        }

        // generate jwt
        return Optional.ofNullable(generateUserJwt(userByEmail.get()));
    }

    @Override
    public Optional<TokenDomain> registerUser(AuthRegisterDomain authRegisterDomain) throws GeneralSecurityException, IOException {
        Optional<AuthUserDomain> userByEmail = findUserByEmail(authRegisterDomain.getEmail());

        if (userByEmail.isPresent()) {
            // User Already exist
            return Optional.empty();
        }

        TokenDomain authJwt = generateAuthJwt();
        Optional<AuthUserDomain> register = userServiceFacade.register(authRegisterDomain, authJwt);

        if (register.isEmpty()) {
            return Optional.empty();
        }

        return Optional.ofNullable(generateUserJwt(register.get()));
    }

    private Optional<AuthUserDomain> findUserByEmail(String email) throws GeneralSecurityException, IOException {
        TokenDomain authJwt = generateAuthJwt();
        return userServiceFacade.findUserByEmail(email, authJwt);
    }

    private TokenDomain generateUserJwt(AuthUserDomain userDomain) throws GeneralSecurityException, IOException {
        String accessToken = jwtGenerateService.generateToken(authDomainMapper.authUserDomainToJwtUser(userDomain));
        return new TokenDomain().setAccessToken(accessToken);
    }

    private TokenDomain generateAuthJwt() throws GeneralSecurityException, IOException {
        String accessToken = jwtGenerateService.generateAuthToken();
        return new TokenDomain().setAccessToken(accessToken);
    }
}
