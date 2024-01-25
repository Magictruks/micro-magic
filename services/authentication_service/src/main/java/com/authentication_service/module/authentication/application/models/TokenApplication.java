package com.authentication_service.module.authentication.application.models;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TokenApplication {
    private String accessToken;
}
