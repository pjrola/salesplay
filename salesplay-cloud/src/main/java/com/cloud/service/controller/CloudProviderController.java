package com.cloud.service.controller;

import com.cloud.service.entity.CloudProvider;
import com.cloud.service.exception.DuplicateResourceException;
import com.cloud.service.exception.ResourceNotFoundException;
import com.cloud.service.service.CloudProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
public class CloudProviderController {

    private CloudProviderService cloudProviderService;

    @Autowired
    public CloudProviderController(CloudProviderService cloudProviderService) {
        this.cloudProviderService = cloudProviderService;
    }

    @GetMapping("/providers/{id}")
    public ResponseEntity<CloudProvider> findById(@PathVariable(value = "id") Long providerId) throws ResourceNotFoundException {
        return new ResponseEntity<>(cloudProviderService.findById(providerId), HttpStatus.OK);
    }

    @GetMapping("/providers")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Page<CloudProvider> findAll(Pageable pageable) {
        return cloudProviderService.findAll(pageable);
    }

    @PostMapping("/providers")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public CloudProvider create(@Valid @RequestBody CloudProvider cloudProvider) throws DuplicateResourceException {
        return cloudProviderService.save(cloudProvider);
    }

    @PutMapping("/providers")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public CloudProvider update(@Valid @RequestBody CloudProvider cloudProvider) throws ResourceNotFoundException {
        return cloudProviderService.update(cloudProvider);
    }

    @DeleteMapping("/providers/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable(value = "id") Long providerId) throws ResourceNotFoundException {
        cloudProviderService.delete(providerId);
    }
}