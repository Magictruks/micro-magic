package com.mongo_crud_service.core.configuration;

import com.mongo_crud_service.core.environment.EnvironmentMongoConfig;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@RequiredArgsConstructor
@EnableMongoRepositories("com.mongo_crud_service")
public class MongoConfig {

    private final EnvironmentMongoConfig environmentMongoConfig;
    @Bean
    public MongoClient mongo() {
        ConnectionString connectionString = new ConnectionString(environmentMongoConfig.getUrl());
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        return MongoClients.create(mongoClientSettings);
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongo(), environmentMongoConfig.getDatabaseName());
    }
}