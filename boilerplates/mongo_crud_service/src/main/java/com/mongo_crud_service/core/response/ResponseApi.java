package com.mongo_crud_service.core.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

@Data
public class ResponseApi<T> {
    private T data;
    private String message;

    public ResponseApi() {}

    public ResponseApi(T _data) {
        data = _data;
    }

    public ResponseApi(String _message) {
        message = _message;
    }

    public ResponseApi(T _data, String _message) {
        data = _data;
        message = _message;
    }
}
