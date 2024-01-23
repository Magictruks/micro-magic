package com.users_service.module.user.application.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserApplicationRegisterDto {
    @Email()
    private String email;
    @NotBlank()
    private String password;
}
