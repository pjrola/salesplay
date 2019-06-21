package com.cloud.service.service;

import com.cloud.service.entity.Instance;
import com.cloud.service.entity.MachineState;
import com.cloud.service.exception.DuplicateResourceException;
import com.cloud.service.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InstanceService {

    Page findAll(Pageable pageable);

    Instance findById(Long id) throws ResourceNotFoundException;

    Instance save(Instance instance) throws DuplicateResourceException;

    Instance update(Instance instance) throws ResourceNotFoundException;

    void delete(Long id) throws ResourceNotFoundException;

    Instance findByOwner(String email) throws ResourceNotFoundException;

    Instance startInstance(Long id) throws ResourceNotFoundException;

    Instance terminateInstance(Long id) throws ResourceNotFoundException;

    Instance stopInstance(Long id) throws ResourceNotFoundException;

    Instance restartInstance(Long id) throws ResourceNotFoundException;

    MachineState getRemoteState(Long id) throws ResourceNotFoundException;
}