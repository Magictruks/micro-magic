package com.authentication_jwt.jwt.mappers;

import com.authentication_jwt.jwt.models.JwtUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface JwtMapper {
    JwtMapper INSTANCE = Mappers.getMapper( JwtMapper.class );
    JwtUser stringToJwtUser(Object data);
}
