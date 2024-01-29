package com.mongo_crud_service.core.environment;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "services")
@Data
public class EnvironmentServiceConfig {
    private String authUrl;
}
