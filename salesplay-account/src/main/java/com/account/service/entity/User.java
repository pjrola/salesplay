package com.account.service.entity;

import com.account.service.entity.base.BaseEntity;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Audited
@Table(name = "users")
public class User extends BaseEntity {

    @NotBlank(message = "{user.email.notNull}")
    @Size(min = 1, max = 100, message = "{user.email.size}")
    @Column(name = "email", unique = true)
    private String email;

    @NotBlank()
    @Size(min = 1, max = 100)
    @Column(name = "password")
    private String password;

    @NotBlank(message = "{user.firstName.notNull}")
    @Size(min = 1, max = 60, message = "{user.firstName.size}")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "{user.lastName.notNull}")
    @Size(min = 1, max = 60, message = "{user.lastName.size}")
    @Column(name = "last_name")
    private String lastName;

    @NotBlank(message = "{user.email.notNull}")
    @Size(min = 1, max = 100 ,message = "{user.email.size}")
    @Column(name = "company")
    private String company;

    @CreatedBy
    @Column(name = "created_by")
    private String createdBy;

    @LastModifiedBy
    @Column(name = "last_modified_by")
    private String lastModifiedBy;

    @Column(name = "enabled")
    private boolean enabled;

    public User(){}

    public User(String email, String password, String firstName, String lastName, String company, Boolean enabled) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.company = company;
        this.enabled = false;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return enabled == user.enabled &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(company, user.company) &&
                Objects.equals(createdBy, user.createdBy) &&
                Objects.equals(lastModifiedBy, user.lastModifiedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password, firstName, lastName, company, createdBy, lastModifiedBy, enabled);
    }
}
