package com.auth.service.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "accounts")
public class Account extends BaseEntity {

    @NotBlank(message = "{account.email.notNull}")
    @Size(min = 1, max = 100, message = "{account.email.size}")
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @NotBlank(message = "{account.password.notNull}")
    @Size(min = 1, max = 100, message = "{account.password.size}")
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "enabled")
    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "account_roles",
            joinColumns = @JoinColumn(
                    name = "account_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles = new HashSet<>();

    public Account() {}

    public Account(String email, String password, boolean enabled, Set<Role> roles) {
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.roles = roles;
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void removeRole(Role role) {
        roles.remove(role);
        role.getAccounts().remove(this);
    }

    public void removeAllRoles() {
        for(Role role : new ArrayList<>(roles)) {
            removeRole(role);
        }
    }

    public void removeRoles(Set<Role> removedRoles) {
        for(Role role : removedRoles) {
            removeRole(role);
        }
    }

    public void addRole(Role role) {
        roles.add(role);
        role.getAccounts().add(this);
    }

    public void addRoles(Set<Role> addedRoles) {
        for(Role role : addedRoles) {
            addRole(role);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return enabled == account.enabled &&
                Objects.equals(email, account.email) &&
                Objects.equals(password, account.password) &&
                Objects.equals(roles, account.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password, enabled, roles);
    }
}
