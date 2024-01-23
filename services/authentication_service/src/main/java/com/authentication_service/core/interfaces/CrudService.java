package com.authentication_service.core.interfaces;

import java.util.List;
import java.util.Optional;

public interface CrudService<T, C, U> {
    List<T> findAll();
    Optional<T> findOneById(Long id);
    T create(C dto);
    T update(Long id, U dto);
    void delete(Long id);
}
