package com.authentication_service.module.authentication.domain.models;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AuthUserDomain {
    private Long id;
    private String email;
    private String userName;
}
