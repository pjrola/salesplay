package com.auth.service.dto;

import com.auth.service.entity.Privilege;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

public class RolePrivilegesDTO {

    @NotBlank(message = "{account.privilegeName.notNull}")
    @Size(min = 1, max = 100, message = "{account.privilegeName.size}")
    private String name;

    private Set<Privilege> privileges;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(Set<Privilege> privileges) {
        this.privileges = privileges;
    }
}
