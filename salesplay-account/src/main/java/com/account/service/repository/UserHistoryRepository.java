package com.account.service.repository;

import com.account.service.entity.User;
import com.account.service.entity.UserHistory;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public class UserHistoryRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public List<UserHistory> listUserRevisionsByUserId(Long userId) {
        AuditReader auditReader = AuditReaderFactory.get(entityManager);

        return auditReader.createQuery()
                .forRevisionsOfEntity(User.class, true, true)
                .add(AuditEntity.id().eq(userId))
                .addOrder(AuditEntity.revisionNumber().asc()).getResultList();
    }

    @Transactional(readOnly = true)
    public List<UserHistory> listUserRevisionsByUserEmail(String email) {
        AuditReader auditReader = AuditReaderFactory.get(entityManager);

        return auditReader.createQuery()
                .forRevisionsOfEntity(User.class, true, true)
                .add(AuditEntity.property("email").eq(email))
                .addOrder(AuditEntity.revisionNumber().asc()).getResultList();
    }

}
