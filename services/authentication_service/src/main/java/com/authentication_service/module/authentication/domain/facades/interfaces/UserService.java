package com.authentication_service.module.authentication.domain.facades.interfaces;

import com.authentication_service.module.authentication.domain.facades.models.UserDomainDto;
import com.authentication_service.module.authentication.domain.facades.models.UserRegisterDto;
import com.authentication_service.module.authentication.domain.models.TokenDomain;

import java.util.Map;
import java.util.Optional;

public interface UserService {
    Optional<UserDomainDto[]> findAll(Map<String, String> query, TokenDomain token);
    Optional<UserDomainDto> findOneByEmail(String email, TokenDomain token);
    Optional<UserDomainDto> create(UserRegisterDto userRegisterDto, TokenDomain tokenDomain);
    boolean checkPassword(String email, String password);

}
