package com.mongo_crud_service.module.entity_name.data.repositories;

import com.mongo_crud_service.module.entity_name.data.models.EntityName;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface EntityNameCrudRepository extends MongoRepository<EntityName, String> {
    Optional<EntityName> findUserByEmail(String email);
    List<EntityName> findAllByEmail(String email);
}
