package com.cloud.service.entity.base;

import com.cloud.service.listener.AuditRevisionListener;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@RevisionEntity(AuditRevisionListener.class)
@Table(name = "revinfo")
public class AuditRevisionEntity extends DefaultRevisionEntity {
    private String user;
}