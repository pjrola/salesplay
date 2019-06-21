package com.cloud.service.service;

import com.cloud.service.entity.CloudProvider;
import com.cloud.service.exception.DuplicateResourceException;
import com.cloud.service.exception.ResourceNotFoundException;
import com.cloud.service.repository.CloudProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CloudProviderServiceImpl implements CloudProviderService {

    private CloudProviderRepository cloudProviderRepository;

    @Autowired
    public CloudProviderServiceImpl(CloudProviderRepository cloudProviderRepository) {
        this.cloudProviderRepository = cloudProviderRepository;
    }

    public Page findAll(Pageable pageable) {
        return cloudProviderRepository.findAll(pageable);
    }

    public CloudProvider findById(Long id) throws ResourceNotFoundException {
        return cloudProviderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id, "not found"));
    }

    public CloudProvider save(CloudProvider cloudProvider) throws DuplicateResourceException {
        Optional<CloudProvider> duplicate = cloudProviderRepository.findByUniqueId(cloudProvider.getUniqueId());

        if (duplicate.isPresent()) {
            throw DuplicateResourceException.createWith(cloudProvider.getUniqueId());
        }
        return cloudProviderRepository.save(cloudProvider);
    }

    public CloudProvider update(CloudProvider cloudProvider) throws ResourceNotFoundException {
        CloudProvider provider = cloudProviderRepository.findById(cloudProvider.getId()).orElseThrow(() -> new ResourceNotFoundException(cloudProvider.getId(), "not found"));
        provider.setBrand(cloudProvider.getBrand());
        provider.setUniqueId(cloudProvider.getUniqueId());
        provider.setEnabled(provider.isEnabled());
        return cloudProviderRepository.save(provider);
    }

    public void delete(Long id) throws ResourceNotFoundException {
        CloudProvider provider = cloudProviderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id, "not found"));
        cloudProviderRepository.delete(provider);
    }
}