package com.auth.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AccountNotFoundException extends Exception {
    private String property;

    public static AccountNotFoundException createWith(String property) {
        return new AccountNotFoundException(property);
    }

    private AccountNotFoundException(String property) {
        this.property = property;
    }

    @Override
    public String getMessage() {
        return "User '" + property + "' not found";
    }
}