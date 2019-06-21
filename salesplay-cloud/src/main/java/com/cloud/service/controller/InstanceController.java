package com.cloud.service.controller;

import com.cloud.service.entity.Instance;
import com.cloud.service.entity.MachineState;
import com.cloud.service.exception.DuplicateResourceException;
import com.cloud.service.exception.ResourceNotFoundException;
import com.cloud.service.service.InstanceService;
import com.cloud.service.service.InstanceServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Slf4j
@RestController
public class InstanceController {

    private InstanceService instanceService;

    @Autowired
    public InstanceController(InstanceServiceImpl instanceService) {
        this.instanceService = instanceService;
    }

    @GetMapping("/instances")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Page<Instance> findAll(Pageable pageable) {
        return instanceService.findAll(pageable);
    }

    @GetMapping("/instances/{id}")
    public ResponseEntity<Instance> findById(@PathVariable(value = "id") Long instanceId) throws ResourceNotFoundException {
        return new ResponseEntity<>(instanceService.findById(instanceId), HttpStatus.OK);
    }

    @GetMapping("/instances/email/{email}")
    public ResponseEntity<Instance> findByEmail(@PathVariable(value = "email") String owner) throws ResourceNotFoundException {
        return new ResponseEntity<>(instanceService.findByOwner(owner), HttpStatus.OK);
    }

    @GetMapping("/instances/start/{id}")
    public ResponseEntity<Instance> start(@PathVariable(value = "id") Long instanceId) throws ResourceNotFoundException {
        return new ResponseEntity<>(instanceService.startInstance(instanceId), HttpStatus.OK);
    }

    @GetMapping("/instances/stop/{id}")
    public ResponseEntity<Instance> stop(@PathVariable(value = "id") Long instanceId) throws ResourceNotFoundException {
        return new ResponseEntity<>(instanceService.stopInstance(instanceId), HttpStatus.OK);
    }

    @GetMapping("/instances/restart/{id}")
    public ResponseEntity<Instance> restart(@PathVariable(value = "id") Long instanceId) throws ResourceNotFoundException {
        return new ResponseEntity<>(instanceService.restartInstance(instanceId), HttpStatus.OK);
    }

    @GetMapping("/instances/state/{id}")
    public ResponseEntity<MachineState> status(@PathVariable(value = "id") Long instanceId) throws ResourceNotFoundException {
        return new ResponseEntity<>(instanceService.getRemoteState(instanceId), HttpStatus.OK);
    }

    @PostMapping("/instances")
    public ResponseEntity<Instance> create(@Valid @RequestBody Instance instance) throws DuplicateResourceException {
        log.info("create called with instance {}", instance);
        return new ResponseEntity<>(instanceService.save(instance), HttpStatus.CREATED);
    }
}