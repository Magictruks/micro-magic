package com.users_service.module.user.application.mappers;

import com.users_service.module.user.application.dtos.*;
import com.users_service.module.user.domain.dtos.UserDomainCreateDto;
import com.users_service.module.user.domain.dtos.UserDomainDto;
import com.users_service.module.user.domain.dtos.UserDomainQueryDto;
import com.users_service.module.user.domain.dtos.UserDomainUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserApplicationCrudMapper {
    UserApplicationCrudMapper INSTANCE = Mappers.getMapper( UserApplicationCrudMapper.class );
    UserApplicationDto dtoFromData(UserDomainDto data);
    List<UserApplicationDto> dtosFromDatas(List<UserDomainDto> data);
    UserDomainCreateDto createDtoToDomain(UserApplicationCreateDto userCreateDto);
    UserDomainUpdateDto updateDtoToData(UserApplicationUpdateDto dto);
    UserDomainQueryDto queryToDomainQuery(Map<String, String> query);
    UserDomainCreateDto registerDtoToDomain(UserApplicationRegisterDto userRegisterDto);

}
