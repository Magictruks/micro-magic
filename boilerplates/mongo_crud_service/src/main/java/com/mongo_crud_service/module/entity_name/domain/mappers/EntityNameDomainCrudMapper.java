package com.mongo_crud_service.module.entity_name.domain.mappers;

import com.mongo_crud_service.module.entity_name.data.models.EntityName;
import com.mongo_crud_service.module.entity_name.domain.dtos.EntityNameDomainCreateDto;
import com.mongo_crud_service.module.entity_name.domain.dtos.EntityNameDomainDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EntityNameDomainCrudMapper {
    EntityNameDomainCrudMapper INSTANCE = Mappers.getMapper( EntityNameDomainCrudMapper.class );
    EntityNameDomainDto dtoFromData(EntityName data);
    List<EntityNameDomainDto> dtosFromDatas(List<EntityName> data);
    EntityName createDtoToData(EntityNameDomainCreateDto dto);
}
