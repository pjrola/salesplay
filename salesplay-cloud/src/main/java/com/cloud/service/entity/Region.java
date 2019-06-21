package com.cloud.service.entity;

import com.cloud.service.entity.base.BaseEntity;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.LastModifiedBy;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Audited
@Getter
@Setter
@Entity
@Table(name = "regions")
public class Region extends BaseEntity {

    @NotBlank(message = "{region.name.notNull}")
    @Size(min = 1, max = 100, message = "{region.name.size}")
    @Column(name = "name")
    private String name;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cloud_provider_id", nullable = false)
    private CloudProvider cloudProvider;

    @NotBlank(message = "{region.uniqueId.notNull}")
    @Size(min = 1, max = 100, message = "{region.uniqueId.size}")
    @Column(name = "region_code")
    private String regionCode;

    @NotBlank(message = "{region.description.notNull}")
    @Size(min = 1, max = 100, message = "{region.description.size}")
    @Column(name = "end_point")
    private String endPoint;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "is_default")
    private boolean isDefault;

    @LastModifiedBy
    @Column(name = "last_modified_by")
    private String lastModifiedBy;

    public Region(){}

    public Region(String name, CloudProvider cloudProvider, String regionCode, String endPoint, Boolean enabled, Boolean isDefault) {
        this.name = name;
        this.regionCode = regionCode;
        this.cloudProvider = cloudProvider;
        this.endPoint = endPoint;
        this.enabled = false;
        this.isDefault = false;
    }

}