package com.mongo_crud_service.module.entity_name.domain.dtos;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class EntityNameDomainRegisterDto {
    private String email;
    private String password;
}
