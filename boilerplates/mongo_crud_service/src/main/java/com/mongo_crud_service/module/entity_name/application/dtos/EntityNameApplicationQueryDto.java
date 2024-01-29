package com.mongo_crud_service.module.entity_name.application.dtos;

import lombok.Data;

import java.util.Map;

@Data
public class EntityNameApplicationQueryDto {
    private Map<String, String> query;
}
