package com.salesplay.content.service.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import static com.google.common.base.Preconditions.checkNotNull;

@Entity
@Getter
@Table(name = "locales")
public class SiteLocale extends PersistentObject {

    @NotEmpty(message = "{locale.name.notNull}")
    @Column(name = "name", nullable = false)
    private String name;

    @NotEmpty(message = "{locale.code.notNull}")
    @Column(name = "code", nullable = false)
    private String code;

    @NotEmpty
    @Column(name = "is_default", nullable = false)
    private Boolean isDefault = false;

    @NotEmpty
    @Column(name = "is_enabled", nullable = false)
    private Boolean isEnabled = false;

    private SiteLocale(){}

    private SiteLocale(String name, String code, Boolean isDefault, Boolean isEnabled) {
        this.name = name;
        this.code = code;
        this.isDefault = isDefault;
        this.isEnabled = isEnabled;
    }

    public static SiteLocale of(String name, String code, Boolean isDefault, Boolean isEnabled) {
        checkNotNull(name);
        checkNotNull(code);
        checkNotNull(isDefault);
        checkNotNull(isEnabled);
        return new SiteLocale(name, code, isDefault, isEnabled);
    }
}