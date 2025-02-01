package com.example.catalog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MissingDataException extends RuntimeException {
    public MissingDataException(String message) {
        super(message);
    }
}
