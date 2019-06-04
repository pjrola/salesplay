package com.cloud.service.entity;

import com.cloud.service.entity.base.BaseEntity;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.springframework.data.annotation.LastModifiedBy;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

@Audited
@Entity
@Table(name = "templates")
public class Template extends BaseEntity {

    @NotBlank(message = "{image.name.notNull}")
    @Size(min = 1, max = 100, message = "{image.name.size}")
    @Column(name = "name", unique = true)
    private String name;

    @NotBlank(message = "{image.name.notNull}")
    @Size(min = 1, max = 100, message = "{image.name.size}")
    @Column(name = "version", unique = true)
    private String version;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "is_default")
    private boolean isDefault;

    @NotAudited
    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "cloud_provider_id", nullable = false)
    private CloudProvider cloudProvider;

    @NotBlank(message = "{image.description.notNull}")
    @Size(min = 1, max = 100, message = "{image.description.size}")
    @Column(name = "description")
    private String description;

    @LastModifiedBy
    @Column(name = "last_modified_by")
    private String lastModifiedBy;

    public Template(){}

    public Template(String name, boolean enabled, boolean isDefault, CloudProvider cloudProvider, String description) {
        this.name = name;
        this.enabled = false;
        this.cloudProvider = cloudProvider;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public CloudProvider getCloudProvider() {
        return cloudProvider;
    }

    public void setCloudProvider(CloudProvider cloudProvider) {
        this.cloudProvider = cloudProvider;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Template)) return false;
        Template template = (Template) o;
        return enabled == template.enabled &&
                isDefault == template.isDefault &&
                Objects.equals(name, template.name) &&
                Objects.equals(version, template.version) &&
                Objects.equals(cloudProvider, template.cloudProvider) &&
                Objects.equals(description, template.description) &&
                Objects.equals(lastModifiedBy, template.lastModifiedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, version, enabled, isDefault, cloudProvider, description, lastModifiedBy);
    }
}