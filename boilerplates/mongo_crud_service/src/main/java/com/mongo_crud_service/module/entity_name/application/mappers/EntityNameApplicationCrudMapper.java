package com.mongo_crud_service.module.entity_name.application.mappers;

import com.mongo_crud_service.module.entity_name.application.dtos.*;
import com.mongo_crud_service.module.entity_name.domain.dtos.EntityNameDomainCreateDto;
import com.mongo_crud_service.module.entity_name.domain.dtos.EntityNameDomainDto;
import com.mongo_crud_service.module.entity_name.domain.dtos.EntityNameDomainQueryDto;
import com.mongo_crud_service.module.entity_name.domain.dtos.EntityNameDomainUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Map;

@Mapper
public interface EntityNameApplicationCrudMapper {
    EntityNameApplicationCrudMapper INSTANCE = Mappers.getMapper( EntityNameApplicationCrudMapper.class );
    EntityNameApplicationDto dtoFromData(EntityNameDomainDto data);
    List<EntityNameApplicationDto> dtosFromDatas(List<EntityNameDomainDto> data);
    EntityNameDomainCreateDto createDtoToDomain(EntityNameApplicationCreateDto userCreateDto);
    EntityNameDomainUpdateDto updateDtoToData(EntityNameApplicationUpdateDto dto);
    EntityNameDomainQueryDto queryToDomainQuery(Map<String, String> query);

}
