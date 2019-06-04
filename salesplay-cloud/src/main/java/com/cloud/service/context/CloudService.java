package com.cloud.service.context;

import com.cloud.service.dto.InstanceDTO;
import com.cloud.service.entity.Instance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CloudService {
    Instance create(Instance instance);
    void delete(String id);
    Instance terminate(Instance instance);
    Instance update(Instance instance);
    Instance getById(String id);
    Page getAll(Pageable pageable);
    Instance getStatusById(String id);
    InstanceDTO restart(String id);
    InstanceDTO stop(String id);
    InstanceDTO start(String id);
}