package com.users_service.module.user.domain.interfaces;

import com.users_service.core.interfaces.CrudService;
import com.users_service.module.user.domain.dtos.UserDomainCreateDto;
import com.users_service.module.user.domain.dtos.UserDomainDto;
import com.users_service.module.user.domain.dtos.UserDomainQueryDto;
import com.users_service.module.user.domain.dtos.UserDomainUpdateDto;

public interface UserCrudService extends CrudService<UserDomainDto, UserDomainCreateDto, UserDomainUpdateDto, UserDomainQueryDto> {
}
