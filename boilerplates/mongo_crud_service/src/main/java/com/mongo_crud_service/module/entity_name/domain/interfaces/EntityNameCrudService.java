package com.mongo_crud_service.module.entity_name.domain.interfaces;

import com.mongo_crud_service.core.interfaces.CrudService;
import com.mongo_crud_service.module.entity_name.domain.dtos.EntityNameDomainCreateDto;
import com.mongo_crud_service.module.entity_name.domain.dtos.EntityNameDomainDto;
import com.mongo_crud_service.module.entity_name.domain.dtos.EntityNameDomainQueryDto;
import com.mongo_crud_service.module.entity_name.domain.dtos.EntityNameDomainUpdateDto;

public interface EntityNameCrudService extends CrudService<EntityNameDomainDto, EntityNameDomainCreateDto, EntityNameDomainUpdateDto, EntityNameDomainQueryDto> {
}
