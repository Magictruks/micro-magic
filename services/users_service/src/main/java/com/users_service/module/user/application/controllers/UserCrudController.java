package com.users_service.module.user.application.controllers;

import com.users_service.core.interfaces.CrudController;
import com.users_service.module.user.application.dtos.UserApplicationCreateDto;
import com.users_service.module.user.application.dtos.UserApplicationDto;
import com.users_service.module.user.application.dtos.UserApplicationUpdateDto;
import com.users_service.module.user.application.facades.UserApplicationToDomainFacade;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserCrudController implements CrudController<UserApplicationDto, UserApplicationCreateDto, UserApplicationUpdateDto> {

    private final UserApplicationToDomainFacade userApplicationToDomainFacade;

    @GetMapping
    public ResponseEntity<List<UserApplicationDto>> findAll(@RequestParam Map<String, String> query) {
        List<UserApplicationDto> list = userApplicationToDomainFacade.findAll(query);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<UserApplicationDto>> findOneById(@PathVariable Long id) {
        Optional<UserApplicationDto> oneById = userApplicationToDomainFacade.findOneById(id);
        return new ResponseEntity<>(oneById, oneById.isPresent() ? HttpStatus.FOUND : HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<UserApplicationDto> create(@Valid @RequestBody UserApplicationCreateDto body) {
        UserApplicationDto userApplicationCreateDto = userApplicationToDomainFacade.create(body);
        return new ResponseEntity<>(userApplicationCreateDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserApplicationDto> updateOneById(@PathVariable Long id, @Valid @RequestBody UserApplicationUpdateDto body) {
        UserApplicationDto update = userApplicationToDomainFacade.update(id, body);
        return new ResponseEntity<>(update, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOneById(@PathVariable Long id) {
        userApplicationToDomainFacade.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
