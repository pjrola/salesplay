package com.cloud.service.entity;

import com.cloud.service.entity.base.BaseEntity;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.LastModifiedBy;
import java.util.Date;
import java.util.Objects;

@Entity
@Audited
@Table(name = "instances")
public class Instance extends BaseEntity {

    @Column(name = "remote_id")
    private String remoteId;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private MachineState state;

    @NotBlank(message = "{instance.assignee.notNull}")
    @Size(min = 1, max = 100, message = "{instance.assignee.size}")
    @Column(name = "assignee", unique = true)
    private String assignee;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "template_id", nullable = false)
    private Template template;

    @Column(name = "locked")
    private boolean locked;

    @Column(name = "url")
    private String url;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_started_time")
    private Date lastStartedTime;

    @LastModifiedBy
    @Column(name = "last_modified_by")
    private String lastModifiedBy;

    public Instance(){}

    public Instance(String remoteId, MachineState state, String assignee, Template template, boolean locked, String url) {
        this.remoteId = remoteId;
        this.state = state;
        this.assignee = assignee;
        this.template = template;
        this.locked = locked;
        this.url = url;
    }

    public MachineState getState() {
        return state;
    }

    public void setState(MachineState state) {
        this.state = state;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getLastStartedTime() {
        return lastStartedTime;
    }

    public void setLastStartedTime(Date lastStartedTime) {
        this.lastStartedTime = lastStartedTime;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public String getRemoteId() {
        return remoteId;
    }

    public void setRemoteId(String remoteId) {
        this.remoteId = remoteId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Instance)) return false;
        Instance instance = (Instance) o;
        return locked == instance.locked &&
                Objects.equals(remoteId, instance.remoteId) &&
                state == instance.state &&
                Objects.equals(assignee, instance.assignee) &&
                Objects.equals(template, instance.template) &&
                Objects.equals(url, instance.url) &&
                Objects.equals(lastStartedTime, instance.lastStartedTime) &&
                Objects.equals(lastModifiedBy, instance.lastModifiedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(remoteId, state, assignee, template, locked, url, lastStartedTime, lastModifiedBy);
    }
}