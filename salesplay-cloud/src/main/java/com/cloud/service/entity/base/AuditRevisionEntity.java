package com.cloud.service.entity.base;

import com.cloud.service.listener.AuditRevisionListener;
import lombok.Getter;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@RevisionEntity(AuditRevisionListener.class)
@Table(name = "revinfo")
public class AuditRevisionEntity extends DefaultRevisionEntity {
    private String user;

    public void setUser(String user) {
        this.user = user;
    }
}