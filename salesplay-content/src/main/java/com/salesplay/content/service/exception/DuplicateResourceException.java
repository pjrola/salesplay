package com.salesplay.content.service.exception;

public class DuplicateResourceException extends Exception {

    private String resource;

    public DuplicateResourceException(String resource) {
        this.resource = resource;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }
}