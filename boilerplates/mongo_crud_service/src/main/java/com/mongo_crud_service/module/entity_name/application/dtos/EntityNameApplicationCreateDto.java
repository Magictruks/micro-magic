package com.mongo_crud_service.module.entity_name.application.dtos;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class EntityNameApplicationCreateDto {
    private String email;
    private String password;
}
