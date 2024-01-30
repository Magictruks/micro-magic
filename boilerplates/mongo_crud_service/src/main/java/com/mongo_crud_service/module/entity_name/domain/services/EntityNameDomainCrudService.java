package com.mongo_crud_service.module.entity_name.domain.services;

import com.mongo_crud_service.module.entity_name.domain.dtos.EntityNameDomainCreateDto;
import com.mongo_crud_service.module.entity_name.domain.dtos.EntityNameDomainDto;
import com.mongo_crud_service.module.entity_name.domain.dtos.EntityNameDomainQueryDto;
import com.mongo_crud_service.module.entity_name.domain.dtos.EntityNameDomainUpdateDto;
import com.mongo_crud_service.module.entity_name.domain.facades.EntityNameDomainToDataFacade;
import com.mongo_crud_service.module.entity_name.domain.interfaces.EntityNameCrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EntityNameDomainCrudService implements EntityNameCrudService {
    private final EntityNameDomainToDataFacade facade;
    @Override
    public List<EntityNameDomainDto> findAll(EntityNameDomainQueryDto queryDto) {
        return facade.findAll(queryDto);
    }

    @Override
    public Optional<EntityNameDomainDto> findOneById(String id) {
        return facade.findOneById(id);
    }

    @Override
    public EntityNameDomainDto create(EntityNameDomainCreateDto dto) {
        return facade.create(dto);
    }
    @Override
    public EntityNameDomainDto update(String id, EntityNameDomainUpdateDto dto) {
        return facade.update(id, dto);
    }

    @Override
    public void delete(String id) {
        facade.delete(id);
    }
}
