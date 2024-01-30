package com.mongo_crud_service.module.entity_name.domain.dtos;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class EntityNameDomainDto {
    private String id;
    private String email;
    private String password;
}
