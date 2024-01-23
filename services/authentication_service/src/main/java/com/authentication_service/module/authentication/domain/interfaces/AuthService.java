package com.authentication_service.module.authentication.domain.interfaces;


import com.authentication_service.module.authentication.domain.models.AuthRegisterDomain;
import com.authentication_service.module.authentication.domain.models.TokenDomain;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Optional;

public interface AuthService {
    Optional<TokenDomain> loginUserByEmailAndPassword(String email, String password) throws GeneralSecurityException, IOException;

    Optional<TokenDomain> registerUser(AuthRegisterDomain authRegisterDomain) throws GeneralSecurityException, IOException;
}
