package com.account.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends Exception {
    private String property;

    public static UserNotFoundException createWith(String property) {
        return new UserNotFoundException(property);
    }

    private UserNotFoundException(String property) {
        this.property = property;
    }

    @Override
    public String getMessage() {
        return "User '" + property + "' not found";
    }
}