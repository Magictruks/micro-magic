package com.users_service.module.user.application.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserApplicationCheckPasswordQuery {
    @Email()
    private String email;
    @NotBlank
    private String password;
}
