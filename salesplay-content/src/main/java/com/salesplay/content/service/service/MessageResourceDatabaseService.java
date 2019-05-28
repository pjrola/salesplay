package com.salesplay.content.service.service;

import com.salesplay.content.service.domain.MessageResource;
import com.salesplay.content.service.exception.DuplicateResourceException;
import com.salesplay.content.service.exception.ResourceNotFoundException;
import com.salesplay.content.service.repository.MessageResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MessageResourceDatabaseService implements MessageResourceService {

    private MessageResourceRepository resourceRepository;

    @Autowired
    public MessageResourceDatabaseService(MessageResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    public MessageResource findByKeyAndLocale(String key, String locale) {
        return null;
    }

    public Iterable<MessageResource> saveAll(Iterable<MessageResource> types) {
        return null;
    }

    public MessageResource save(MessageResource type) throws DuplicateResourceException {
        return null;
    }

    public Page<MessageResource> findAll(Pageable pageable) {
        return null;
    }

    public Iterable findAllById(Iterable ids) {
        return null;
    }

    public MessageResource findById(Long id) throws ResourceNotFoundException {
        return null;
    }

    public void deleteById(Long id) throws ResourceNotFoundException {

    }

    public void delete(MessageResource type) throws ResourceNotFoundException {

    }

    public void deleteAll() {

    }

    public void deleteAll(Iterable<MessageResource> types) {

    }

    public MessageResource update(MessageResource type) throws ResourceNotFoundException {
        return null;
    }

    public Long count() {
        return null;
    }

    public Boolean existsById(Long id) throws ResourceNotFoundException {
        return null;
    }
}
