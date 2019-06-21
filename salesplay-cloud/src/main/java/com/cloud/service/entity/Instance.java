package com.cloud.service.entity;

import com.cloud.service.entity.base.BaseEntity;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.LastModifiedBy;
import java.util.Date;

@Entity
@Getter
@Setter
@EqualsAndHashCode
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

}