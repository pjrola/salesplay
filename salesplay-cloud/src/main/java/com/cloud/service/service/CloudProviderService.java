package com.cloud.service.service;

import com.cloud.service.entity.CloudProvider;
import com.cloud.service.exception.DuplicateResourceException;
import com.cloud.service.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CloudProviderService {

    Page findAll(Pageable pageable);

    CloudProvider findById(Long id) throws ResourceNotFoundException;

    CloudProvider save(CloudProvider cloudProvider) throws DuplicateResourceException;

    CloudProvider update(CloudProvider cloudProvider) throws ResourceNotFoundException;

    void delete(Long id) throws ResourceNotFoundException;

}