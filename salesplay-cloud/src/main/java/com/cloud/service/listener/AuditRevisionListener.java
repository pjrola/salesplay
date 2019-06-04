package com.cloud.service.listener;

import com.cloud.service.entity.base.AuditRevisionEntity;
import org.hibernate.envers.RevisionListener;

public class AuditRevisionListener implements RevisionListener {
    @Override
    public void newRevision(Object revisionEntity) {
        AuditRevisionEntity rev = (AuditRevisionEntity) revisionEntity;
        rev.setUser("MYUSER");
    }
}