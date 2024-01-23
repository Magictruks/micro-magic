package com.authentication_jwt.jwt.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "jwt")
@Data
public class JwtConfig {
    private String publicKeyPath;
    private String publicKey;
    private String privateKeyPath;
    private long expirationTime;
}
