package com.cloud.service.dto;

import com.cloud.service.entity.MachineState;
import com.cloud.service.entity.Template;
import javax.persistence.*;
import java.util.Date;

public class InstanceDTO {

    private String remoteId;

    private MachineState state;

    private String assignee;

    private Template template;

    private boolean locked;

    private String url;

    @Column(name = "last_started_time")
    private Date lastStartedTime;

    @Column(name = "last_modified_by")
    private String lastModifiedBy;

    public String getRemoteId() {
        return remoteId;
    }

    public void setRemoteId(String remoteId) {
        this.remoteId = remoteId;
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
}
