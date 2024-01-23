package com.users_service.module.user.domain.dtos;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserDomainDto {
    private Long id;
    private String email;
    private String password;
}
