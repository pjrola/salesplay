package com.salesplay.content.service.exception;

public class ResourceNotFoundException extends Exception {

    private String property;

    public ResourceNotFoundException(String property) {
        this.property = property;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

}