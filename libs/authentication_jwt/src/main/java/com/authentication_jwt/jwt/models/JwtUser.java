package com.authentication_jwt.jwt.models;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class JwtUser {
    private String id;
    private String email;
    private String userName;

}
