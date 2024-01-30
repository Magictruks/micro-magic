package com.mongo_crud_service.module.entity_name.domain.facades;


import com.mongo_crud_service.core.interfaces.CrudFacade;
import com.mongo_crud_service.module.entity_name.data.models.EntityName;
import com.mongo_crud_service.module.entity_name.data.repositories.EntityNameCrudRepository;
import com.mongo_crud_service.module.entity_name.domain.dtos.EntityNameDomainCreateDto;
import com.mongo_crud_service.module.entity_name.domain.dtos.EntityNameDomainDto;
import com.mongo_crud_service.module.entity_name.domain.dtos.EntityNameDomainQueryDto;
import com.mongo_crud_service.module.entity_name.domain.dtos.EntityNameDomainUpdateDto;
import com.mongo_crud_service.module.entity_name.domain.mappers.EntityNameDomainCrudMapper;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EntityNameDomainToDataFacade implements CrudFacade<EntityNameDomainDto, EntityNameDomainCreateDto, EntityNameDomainUpdateDto, EntityNameDomainQueryDto> {
    private final EntityNameCrudRepository crudRepository;
    private final EntityNameDomainCrudMapper mapper;
    public List<EntityNameDomainDto> findAll(EntityNameDomainQueryDto queryDto) {
        List<EntityName> data;
        if (Strings.isNotEmpty(queryDto.getEmail())) {
            data = crudRepository.findAllByEmail(queryDto.getEmail());
        } else {
            data = crudRepository.findAll();
        }
        return mapper.dtosFromDatas(data);
    }

    public Optional<EntityNameDomainDto> findOneById(String id) {
        Optional<EntityName> data = crudRepository.findById(id);
        return data.map(mapper::dtoFromData).or(Optional::empty);
    }

    public Optional<EntityNameDomainDto> findOneByEmail(String email) {
        Optional<EntityName> data = crudRepository.findUserByEmail(email);
        return data.map(mapper::dtoFromData).or(Optional::empty);
    }
    public EntityNameDomainDto create(EntityNameDomainCreateDto dto) {
        EntityName mapperDtoToData = mapper.createDtoToData(dto);
        EntityName save = crudRepository.save(mapperDtoToData);
        return mapper.dtoFromData(save);
    }

    public EntityNameDomainDto update(String id, EntityNameDomainUpdateDto dto) {
        Optional<EntityName> userDB = crudRepository.findById(id);

        if (userDB.isEmpty()) {
            return null;
        }

        EntityName user = userDB.get()
                .setEmail(dto.getEmail());

        crudRepository.save(user);
        return mapper.dtoFromData(user);
    }

    public void delete(String id) {
        crudRepository.deleteById(id);
    }
}
