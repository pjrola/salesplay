package com.salesplay.content.service.service;

import com.salesplay.content.service.domain.MessageResource;
import com.salesplay.content.service.exception.DuplicateResourceException;
import com.salesplay.content.service.exception.ResourceNotFoundException;
import com.salesplay.content.service.repository.MessageResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Locale;

@Service
public class MessageByLocaleDatabaseService implements MessageByLocaleService {

    private MessageResourceRepository repository;

    private MessageSource messageSource;

    @Autowired
    public MessageByLocaleDatabaseService(MessageResourceRepository repository, MessageSource messageSource) {
        this.repository = repository;
        this.messageSource = messageSource;
    }

    public String getMessage(String code) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(code,null, locale);
    }

    public String getMessageWithParam(String code, Object[] var2) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(code, var2, locale);
    }

    public Iterable<MessageResource> saveAll(Iterable<MessageResource> resources) {
        return repository.saveAll(resources);
    }

    public MessageResource create(MessageResource resource) throws DuplicateResourceException {

        if (existsByKeyAndLocale(resource.getKey(), resource.getLocale())) {
            throw new DuplicateResourceException(resource.getKey());
        }

        return repository.save(resource);
    }

    public MessageResource update(MessageResource resource) throws ResourceNotFoundException {
        if (!repository.existsById(resource.getId())) {
            throw new ResourceNotFoundException(resource.getId().toString());
        }

        return repository.save(resource);
    }

    public Page<MessageResource> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Iterable<Long> findAllById(Iterable ids) {
        return repository.findAllById(ids);
    }

    public MessageResource findById(Long id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException(id.toString()));
    }

    public void deleteById(Long id) throws ResourceNotFoundException {
        MessageResource resource = repository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException(id.toString()));
        repository.deleteById(resource.getId());
    }

    public void delete(MessageResource messageResource) throws ResourceNotFoundException {
        MessageResource resource = repository.findById(messageResource.getId()).orElseThrow(()
                -> new ResourceNotFoundException(messageResource.getId().toString()));
        repository.delete(resource);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public void deleteAll(Iterable<MessageResource> resources) {
        repository.deleteAll(resources);
    }

    public Boolean existsByKeyAndLocale(String key, String locale) {
        return repository.existsByKeyAndLocale(key, locale);
    }
}