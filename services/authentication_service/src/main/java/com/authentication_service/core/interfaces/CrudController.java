package com.authentication_service.core.interfaces;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public interface CrudController<T, C, U> {
    @GetMapping
    ResponseEntity<List<T>> findAll();

    @GetMapping("/{id}")
    ResponseEntity<Optional<T>> findOneById(@PathVariable Long id);

    @PostMapping
    ResponseEntity<T> create(@Valid @RequestBody C body);

    @PutMapping("/{id}")
    ResponseEntity<T> updateOneById(@PathVariable Long id, @Valid @RequestBody U body);

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteOneById(@PathVariable Long id);
}
