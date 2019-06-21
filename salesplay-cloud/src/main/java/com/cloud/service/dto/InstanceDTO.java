package com.cloud.service.dto;

import com.cloud.service.entity.MachineState;
import com.cloud.service.entity.Template;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class InstanceDTO {
    private String remoteId;
    private MachineState state;
    private String assignee;
    private Template template;
    private boolean locked;
    private String url;
    private Date lastStartedTime;
    private String lastModifiedBy;
}
