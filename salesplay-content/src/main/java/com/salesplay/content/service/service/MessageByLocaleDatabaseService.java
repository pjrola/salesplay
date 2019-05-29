package com.salesplay.content.service.service;

import com.salesplay.content.service.domain.MessageResource;
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
        return messageSource.getMessage(code,var2,locale);
    }

    public MessageResource findByKeyAndLocale(String key, String locale) {
        return repository.findByKeyAndLocale(key, locale);
    }

    public Iterable<MessageResource> saveAll(Iterable<MessageResource> types) {
        return null;
    }

    public MessageResource save(MessageResource type) {
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
}