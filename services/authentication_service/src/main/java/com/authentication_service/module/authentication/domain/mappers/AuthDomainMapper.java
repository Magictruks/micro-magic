package com.authentication_service.module.authentication.domain.mappers;

import com.authentication_service.module.authentication.domain.facades.models.UserDomainDto;
import com.authentication_service.module.authentication.domain.facades.models.UserRegisterDto;
import com.authentication_service.module.authentication.domain.models.AuthRegisterDomain;
import com.authentication_service.module.authentication.domain.models.AuthUserDomain;
import com.authentication_jwt.jwt.models.JwtUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthDomainMapper {
    AuthDomainMapper INSTANCE = Mappers.getMapper(AuthDomainMapper.class);

    AuthUserDomain userToAuthUserDomain(UserDomainDto data);

    UserRegisterDto registerDomainToDto(AuthRegisterDomain data);

    JwtUser authUserDomainToJwtUser(AuthUserDomain user);
}
