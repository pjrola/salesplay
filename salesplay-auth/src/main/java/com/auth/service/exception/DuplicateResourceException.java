package com.auth.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateResourceException extends Exception {
    private String resource;

    public static DuplicateResourceException createWith(String resource) {
        return new DuplicateResourceException(resource);
    }

    private DuplicateResourceException(String resource) {
        this.resource = resource;
    }

    @Override
    public String getMessage() {
        return "Duplicate resource '" + resource + "' already exists";
    }
}