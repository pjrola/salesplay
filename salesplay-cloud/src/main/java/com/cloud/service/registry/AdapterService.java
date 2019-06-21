package com.cloud.service.registry;

import com.cloud.service.dto.InstanceDTO;
import com.cloud.service.entity.Instance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdapterService<T> {
    Instance create(T request);
    void delete(String id);
    Instance terminate(T request);
    Instance update(T request);
    Instance getById(String id);
    Page getAll(Pageable pageable);
    Instance getStatusById(String id);
    InstanceDTO restart(String id);
    InstanceDTO stop(String id);
    InstanceDTO start(String id);
}
