package com.cloud.service.entity;

import com.cloud.service.entity.base.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.springframework.data.annotation.LastModifiedBy;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Audited
@Getter
@Setter
@EqualsAndHashCode
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

}