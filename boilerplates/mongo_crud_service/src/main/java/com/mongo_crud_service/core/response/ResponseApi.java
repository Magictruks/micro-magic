package com.mongo_crud_service.core.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

@Data
public class ResponseApi<T> {
    private T data;

    public ResponseApi() {}

    public ResponseApi(T _data) {
        data = _data;
    }
}
