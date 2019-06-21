package com.cloud.service.service;

import com.cloud.service.dto.InstanceDTO;
import com.cloud.service.entity.Instance;
import com.cloud.service.entity.MachineState;
import com.cloud.service.exception.DuplicateResourceException;
import com.cloud.service.exception.ResourceNotFoundException;
import com.cloud.service.registry.ServiceRegistry;
import com.cloud.service.repository.InstanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class InstanceServiceImpl implements InstanceService {

    private InstanceRepository repository;

    private ServiceRegistry serviceRegistry;

    @Autowired
    public InstanceServiceImpl(InstanceRepository repository, ServiceRegistry serviceRegistry) {
        this.repository = repository;
        this.serviceRegistry = serviceRegistry;
    }

    public Page findAll(Pageable pageable) {
        return this.repository.findAll(pageable);
    }

    public Instance findById(Long id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException(id.toString()));
    }

    public Instance save(Instance instance) throws DuplicateResourceException {
        if (repository.existsByOwner(instance.getOwner())) {
            throw new DuplicateResourceException(instance.getOwner());
        }

        Instance remote = serviceRegistry.getService("amazonWebServices").create(instance);
        return repository.save(remote);
    }

    public Instance update(Instance instance) throws ResourceNotFoundException {
        return null;
    }

    public void delete(Long id) throws ResourceNotFoundException {
        Instance instance = this.findById(id);
        serviceRegistry.getService("amazonWebServices").delete(id.toString());
        repository.delete(instance);
    }

    public Instance findByOwner(String email) throws ResourceNotFoundException {
        return repository.findByOwner(email).orElseThrow(() -> new ResourceNotFoundException(email));
    }

    /**
     * Start Remote instance
     * set status to running
     *
     * @param id
     * @return
     * @throws ResourceNotFoundException
     */
    public Instance startInstance(Long id) throws ResourceNotFoundException {
        Instance instance = this.findById(id);
        InstanceDTO dto = serviceRegistry.getService("amazonWebServices").start(instance.getRemoteId());
        instance.setState(dto.getState());
        return repository.save(instance);
    }

    /**
     * Terminate Remote instance
     * set status to terminated
     *
     * @param id
     * @return
     * @throws ResourceNotFoundException
     */
    public Instance terminateInstance(Long id) throws ResourceNotFoundException {
        Instance instance = serviceRegistry.getService("amazonWebServices").terminate(this.findById(id));
        instance.setState(MachineState.terminated);
        return repository.save(instance);
    }

    /**
     * Stop Remote instance
     * update status to stopped
     *
     * @param id
     * @return
     * @throws ResourceNotFoundException
     */
    public Instance stopInstance(Long id) throws ResourceNotFoundException {
        Instance instance = this.findById(id);
        InstanceDTO dto = serviceRegistry.getService("amazonWebServices").stop(instance.getRemoteId());
        instance.setState(dto.getState());
        return repository.save(instance);
    }

    public Instance restartInstance(Long id) throws ResourceNotFoundException {
        Instance instance = this.findById(id);
        InstanceDTO dto = serviceRegistry.getService("amazonWebServices").restart(instance.getRemoteId());
        return repository.save(instance);
    }

    public MachineState getRemoteState(Long id) throws ResourceNotFoundException {
        Instance instance = this.findById(id);
        return instance.getState();
    }
}