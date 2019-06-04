package com.auth.service.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AccountUpdateDTO {

    @NotNull(message = "{account.accountId.notNull}")
    private Long id;

    @NotBlank(message = "{account.email.notNull}")
    @Size(min = 1, max = 100, message = "{account.email.size}")
    private String email;

    @NotBlank(message = "{account.password.notNull}")
    @Size(min = 1, max = 100, message = "{account.password.size}")
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
