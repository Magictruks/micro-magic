package com.authentication_service.module.authentication.domain.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class AuthRegisterDomain extends AuthUserDomain {
    private String password;
}
