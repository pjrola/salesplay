package com.cloud.service.entity;

import com.cloud.service.entity.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Audited
@Entity
@Getter
@Setter
@Table(name = "cloud_providers")
public class CloudProvider extends BaseEntity {

    @NotBlank(message = "{cloudprovider.brand.notNull}")
    @Size(min = 1, max = 100, message = "{cloudprovider.brand.size}")
    @Column(name = "brand", unique = true)
    private String brand;

    @NotBlank(message = "{cloudprovider.uniqueId.notNull}")
    @Size(min = 1, max = 100, message = "{cloudprovider.uniqueId.size}")
    @Column(name = "unique_id", unique = true)
    private String uniqueId;

    @Column(name = "last_modified_by")
    private String lastModifiedBy;

    @Column(name = "enabled")
    private boolean enabled;

    public CloudProvider(){}

    public CloudProvider(String brand, String uniqueId) {
        this.brand = brand;
        this.uniqueId = uniqueId;
        this.enabled = false;
    }
}