package com.mongo_crud_service.core.environment;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "mongo")
@Data
public class EnvironmentMongoConfig {
    private String url;
    private String databaseName;
}
