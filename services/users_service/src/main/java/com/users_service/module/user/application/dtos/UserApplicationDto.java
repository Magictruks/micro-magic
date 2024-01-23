package com.users_service.module.user.application.dtos;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserApplicationDto {
    private String email;
}
