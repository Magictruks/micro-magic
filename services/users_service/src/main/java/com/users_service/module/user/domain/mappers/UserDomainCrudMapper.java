package com.users_service.module.user.domain.mappers;

import com.users_service.module.user.data.models.User;
import com.users_service.module.user.domain.dtos.UserDomainCreateDto;
import com.users_service.module.user.domain.dtos.UserDomainDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserDomainCrudMapper {
    UserDomainCrudMapper INSTANCE = Mappers.getMapper( UserDomainCrudMapper.class );
    UserDomainDto dtoFromData(User data);
    List<UserDomainDto> dtosFromDatas(List<User> data);
    User createDtoToData(UserDomainCreateDto dto);
}
