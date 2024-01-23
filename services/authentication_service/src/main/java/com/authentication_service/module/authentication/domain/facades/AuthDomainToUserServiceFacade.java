package com.authentication_service.module.authentication.domain.facades;

import com.authentication_service.module.authentication.domain.facades.interfaces.UserService;
import com.authentication_service.module.authentication.domain.facades.models.UserDomainDto;
import com.authentication_service.module.authentication.domain.facades.models.UserRegisterDto;
import com.authentication_service.module.authentication.domain.mappers.AuthDomainMapper;
import com.authentication_service.module.authentication.domain.models.AuthRegisterDomain;
import com.authentication_service.module.authentication.domain.models.AuthUserDomain;
import com.authentication_service.module.authentication.domain.models.TokenDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthDomainToUserServiceFacade {
    private final AuthDomainMapper authDomainMapper;
    private final UserService userService;

    public Optional<AuthUserDomain> findUserByEmail(String email, TokenDomain token) {
        Optional<UserDomainDto> user = userService.findOneByEmail(email, token);
        return user.map(authDomainMapper::userToAuthUserDomain);
    }

    public Optional<AuthUserDomain> register(AuthRegisterDomain registerDomain, TokenDomain tokenDomain) {
        UserRegisterDto userRegisterDto = authDomainMapper.registerDomainToDto(registerDomain);
        Optional<UserDomainDto> user = userService.create(userRegisterDto, tokenDomain);
        return user.map(authDomainMapper::userToAuthUserDomain);
    }

    public boolean checkPassword(String email, String password) {
        return userService.checkPassword(email, password);
    }
}
