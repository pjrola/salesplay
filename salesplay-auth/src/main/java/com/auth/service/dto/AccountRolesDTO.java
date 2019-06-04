package com.auth.service.dto;

import com.auth.service.entity.Role;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;

public class AccountRolesDTO {

    @NotBlank(message = "{account.email.notNull}")
    @Size(min = 1, max = 100, message = "{account.email.size}")
    private String email;

    private Collection<Role> roles;

    public String getemail() {
        return email;
    }

    public void setemail(String email) {
        this.email = email;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}
