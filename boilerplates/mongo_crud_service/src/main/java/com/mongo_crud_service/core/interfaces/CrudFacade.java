package com.mongo_crud_service.core.interfaces;

import java.util.List;
import java.util.Optional;

public interface CrudFacade<T, C, U, Q> {
     List<T> findAll(Q query);

     Optional<T> findOneById(Long id);

     T create(C dto);

     T update(Long id, U dto);

     void delete(Long id);
}
