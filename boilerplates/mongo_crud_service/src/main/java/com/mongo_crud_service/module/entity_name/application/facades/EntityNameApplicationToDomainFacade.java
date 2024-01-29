package com.mongo_crud_service.module.entity_name.application.facades;

import com.mongo_crud_service.core.interfaces.CrudFacade;
import com.mongo_crud_service.module.entity_name.application.dtos.EntityNameApplicationCreateDto;
import com.mongo_crud_service.module.entity_name.application.dtos.EntityNameApplicationDto;
import com.mongo_crud_service.module.entity_name.application.dtos.EntityNameApplicationUpdateDto;
import com.mongo_crud_service.module.entity_name.application.mappers.EntityNameApplicationCrudMapper;
import com.mongo_crud_service.module.entity_name.domain.dtos.EntityNameDomainCreateDto;
import com.mongo_crud_service.module.entity_name.domain.dtos.EntityNameDomainDto;
import com.mongo_crud_service.module.entity_name.domain.dtos.EntityNameDomainQueryDto;
import com.mongo_crud_service.module.entity_name.domain.dtos.EntityNameDomainUpdateDto;
import com.mongo_crud_service.module.entity_name.domain.services.EntityNameDomainCrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EntityNameApplicationToDomainFacade implements CrudFacade<EntityNameApplicationDto, EntityNameApplicationCreateDto, EntityNameApplicationUpdateDto, Map<String, String>> {
    private final EntityNameDomainCrudService domainCrudService;
    private final EntityNameApplicationCrudMapper mapper;
    public List<EntityNameApplicationDto> findAll(Map<String, String> query) {
        EntityNameDomainQueryDto queryDto = mapper.queryToDomainQuery(query);
        List<EntityNameDomainDto> data = domainCrudService.findAll(queryDto);
        return mapper.dtosFromDatas(data);
    }

    public Optional<EntityNameApplicationDto> findOneById(Long id) {
        Optional<EntityNameDomainDto> data = domainCrudService.findOneById(id);
        return data.map(mapper::dtoFromData);
    }

    public EntityNameApplicationDto create(EntityNameApplicationCreateDto dto) {
        EntityNameDomainCreateDto mapperDtoToData = mapper.createDtoToDomain(dto);
        EntityNameDomainDto userDomainCreateDto = domainCrudService.create(mapperDtoToData);
        return mapper.dtoFromData(userDomainCreateDto);
    }

    public EntityNameApplicationDto update(Long id, EntityNameApplicationUpdateDto dto) {
        EntityNameDomainUpdateDto mapperDtoToData = mapper.updateDtoToData(dto);
        EntityNameDomainDto userDomainCreateDto = domainCrudService.update(id, mapperDtoToData);
        return mapper.dtoFromData(userDomainCreateDto);
    }

    public void delete(Long id) {
        domainCrudService.delete(id);
    }
}
