package com.auth.service.exception;

public class ResourceNotFoundException extends Exception {

    private Long resourceId;

    public ResourceNotFoundException(Long resourceId, String message) {
        super(message);
        this.resourceId = resourceId;
    }

}
