package com.mongo_crud_service.core.interfaces;

import java.util.List;
import java.util.Optional;

public interface CrudFacade<T, C, U, Q> {
     List<T> findAll(Q query);

     Optional<T> findOneById(String id);

     T create(C dto);

     T update(String id, U dto);

     void delete(String id);
}
