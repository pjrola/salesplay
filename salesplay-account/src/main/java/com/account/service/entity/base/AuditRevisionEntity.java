package com.account.service.entity.base;

import com.account.service.listener.AuditRevisionListener;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@RevisionEntity( AuditRevisionListener.class )
@Table(name = "revinfo")
public class AuditRevisionEntity extends DefaultRevisionEntity {
    private String user;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
