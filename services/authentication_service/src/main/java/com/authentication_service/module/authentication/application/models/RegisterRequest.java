package com.authentication_service.module.authentication.application.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterRequest {
    @Email()
    private String email;
    @NotBlank()
    private String password;
}
