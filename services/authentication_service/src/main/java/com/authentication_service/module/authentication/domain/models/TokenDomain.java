package com.authentication_service.module.authentication.domain.models;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TokenDomain {
    private String accessToken;
}
