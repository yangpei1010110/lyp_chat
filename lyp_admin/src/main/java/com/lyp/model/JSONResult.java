package com.lyp.model;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

public class JSONResult<T> extends ResponseEntity<T> {
    private JSONResult(HttpStatus status) {
        super(status);
    }

    private JSONResult(T body, HttpStatus status) {
        super(body, status);
    }

    private JSONResult(MultiValueMap<String, String> headers, HttpStatus status) {
        super(headers, status);
    }

    private JSONResult(T body, MultiValueMap<String, String> headers, HttpStatus status) {
        super(body, headers, status);
    }
}
