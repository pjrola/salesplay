package com.auth.service.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

    @NotBlank(message = "{account.roleName.notNull}")
    @Size(min = 1, max = 100, message = "{account.roleName.size}")
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @NotBlank(message = "{account.roleDescription.notNull}")
    @Size(min = 1, max = 500, message = "{account.roleDescription.size}")
    @Column(name = "description", nullable = false)
    private String description;

    @JsonBackReference
    @ManyToMany(mappedBy = "roles")
    private Set<Account> accounts = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "roles_privileges",
            joinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "privilege_id", referencedColumnName = "id"))
    private Set<Privilege> privileges = new HashSet<>();

    public Role() {}

    public Role(String name, String description, Set<Account> accounts, Set<Privilege> privileges) {
        this.name = name;
        this.description = description;
        this.accounts = accounts;
        this.privileges = privileges;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

    public Set<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(Set<Privilege> privileges) {
        this.privileges = privileges;
    }

    public void removePrivilege(Privilege privilege) {
        privileges.remove(privilege);
        privilege.getRoles().remove(this);
    }

    public void removeAllPrivileges() {
        for(Privilege privilege : new ArrayList<>(privileges)) {
            removePrivilege(privilege);
        }
    }

    public void removePrivileges(Set<Privilege> removedPrivileges) {
        for(Privilege privilege : removedPrivileges) {
            removePrivilege(privilege);
        }
    }

    public void addPrivilege(Privilege privilege) {
        privileges.add(privilege);
        privilege.getRoles().add(this);
    }

    public void addPrivileges(Set<Privilege> addedPrivileges) {
        for(Privilege privilege : addedPrivileges) {
            addPrivilege(privilege);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(name, role.name) &&
                Objects.equals(description, role.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }
}
