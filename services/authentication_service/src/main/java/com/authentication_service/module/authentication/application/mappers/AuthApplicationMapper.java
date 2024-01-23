package com.authentication_service.module.authentication.application.mappers;

import com.authentication_service.module.authentication.application.models.RegisterRequest;
import com.authentication_service.module.authentication.application.models.TokenApplication;
import com.authentication_service.module.authentication.domain.models.AuthRegisterDomain;
import com.authentication_service.module.authentication.domain.models.TokenDomain;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AuthApplicationMapper {
    AuthApplicationMapper INSTANCE = Mappers.getMapper( AuthApplicationMapper.class );

    TokenApplication tokenFromDomain(TokenDomain tokenDomain);
    AuthRegisterDomain registerToDomain (RegisterRequest register);
}
