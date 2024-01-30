package com.mongo_crud_service.core.interfaces;

import com.mongo_crud_service.core.response.ResponseApi;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CrudController<T, C, U> {
    @GetMapping
    ResponseEntity<ResponseApi<List<T>>> findAll(Map<String, String> query);

    @GetMapping("/{id}")
    ResponseEntity<ResponseApi<Optional<T>>> findOneById(@PathVariable String id);

    @PostMapping
    ResponseEntity<ResponseApi<T>> create(@Valid @RequestBody C body);

    @PutMapping("/{id}")
    ResponseEntity<ResponseApi<T>> updateOneById(@PathVariable String id, @Valid @RequestBody U body);

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseApi<String>> deleteOneById(@PathVariable String id);
}
