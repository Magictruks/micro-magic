package com.mongo_crud_service.module.entity_name.application.controllers;

import com.mongo_crud_service.core.interfaces.CrudController;
import com.mongo_crud_service.core.response.ResponseApi;
import com.mongo_crud_service.module.entity_name.application.dtos.EntityNameApplicationCreateDto;
import com.mongo_crud_service.module.entity_name.application.dtos.EntityNameApplicationDto;
import com.mongo_crud_service.module.entity_name.application.dtos.EntityNameApplicationUpdateDto;
import com.mongo_crud_service.module.entity_name.application.facades.EntityNameApplicationToDomainFacade;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/entityName")
public class EntityNameApplicationCrudController implements CrudController<EntityNameApplicationDto, EntityNameApplicationCreateDto, EntityNameApplicationUpdateDto>
{

    private final EntityNameApplicationToDomainFacade facade;

    @GetMapping
    public ResponseEntity<ResponseApi<List<EntityNameApplicationDto>>> findAll(@RequestParam Map<String, String> query) {
        List<EntityNameApplicationDto> list = facade.findAll(query);
        return new ResponseEntity<>(new ResponseApi<>(list), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseApi<Optional<EntityNameApplicationDto>>> findOneById(@PathVariable String id) {
        Optional<EntityNameApplicationDto> oneById = facade.findOneById(id);
        return new ResponseEntity<>(new ResponseApi<>(oneById), oneById.isPresent() ? HttpStatus.FOUND : HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<ResponseApi<EntityNameApplicationDto>> create(@Valid @RequestBody EntityNameApplicationCreateDto body) {
        EntityNameApplicationDto userApplicationCreateDto = facade.create(body);
        return new ResponseEntity<>(new ResponseApi<>(userApplicationCreateDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseApi<EntityNameApplicationDto>> updateOneById(@PathVariable String id, @Valid @RequestBody EntityNameApplicationUpdateDto body) {
        EntityNameApplicationDto update = facade.update(id, body);
        return new ResponseEntity<>(new ResponseApi<>(update), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseApi<EntityNameApplicationDto>> deleteOneById(@PathVariable String id) {
//        throw new ResponseStatusException(
//                HttpStatus.NOT_FOUND, "Foo Not Found");
//
        facade.delete(id);
        return new ResponseEntity<>(new ResponseApi<>("Is OK"), HttpStatus.OK);
    }
}
