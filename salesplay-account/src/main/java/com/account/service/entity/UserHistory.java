package com.account.service.entity;

import org.hibernate.envers.RevisionType;

public class UserHistory {

    private final User user;
    private final Number revision;
    private final RevisionType revisionType;

    public UserHistory(User user, Number revision, RevisionType revisionType) {
        this.user = user;
        this.revision = revision;
        this.revisionType = revisionType;
    }

    public User getUser() {
        return user;
    }

    public Number getRevision() {
        return revision;
    }

    public RevisionType getRevisionType() {
        return revisionType;
    }

}
