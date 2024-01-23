package com.authentication_service.module.authentication.domain.facades.models;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserRegisterDto {
    private String email;
    private String password;
}
