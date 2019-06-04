package com.auth.service.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "privileges")
public class Privilege extends BaseEntity {

    @NotBlank(message = "{account.privilegeName.notNull}")
    @Size(min = 1, max = 100, message = "{account.privilegeName.size}")
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @NotBlank(message = "{account.roleDescription.notNull}")
    @Size(min = 1, max = 500, message = "{account.roleDescription.size}")
    @Column(name = "description", nullable = false)
    private String description;

    @JsonBackReference
    @ManyToMany(mappedBy = "privileges")
    private Set<Role> roles = new HashSet<>();

    public Privilege() {}

    public Privilege(String name, String description, Set<Role> roles) {
        this.name = name;
        this.description = description;
        this.roles = roles;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Privilege privilege = (Privilege) o;
        return Objects.equals(name, privilege.name) &&
                Objects.equals(description, privilege.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }
}