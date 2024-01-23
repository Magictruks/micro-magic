package com.authentication_service.module.authentication.application.models;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
