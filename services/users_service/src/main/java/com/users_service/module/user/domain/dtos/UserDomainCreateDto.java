package com.users_service.module.user.domain.dtos;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserDomainCreateDto {
    private String email;
    private String password;
}
