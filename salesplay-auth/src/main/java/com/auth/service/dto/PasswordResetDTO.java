package com.auth.service.dto;

import javax.validation.constraints.NotEmpty;

public class PasswordResetDTO {

    @NotEmpty
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}