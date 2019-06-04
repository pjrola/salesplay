package com.cloud.service.entity;

import com.cloud.service.entity.base.BaseEntity;
import org.hibernate.envers.Audited;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Audited
@Entity
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}