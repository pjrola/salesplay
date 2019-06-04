package com.cloud.service.service;

import com.cloud.service.entity.Template;
import com.cloud.service.exception.DuplicateResourceException;
import com.cloud.service.exception.ResourceNotFoundException;
import com.cloud.service.repository.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TemplateServiceImpl implements TemplateService {

    private TemplateRepository templateRepository;

    @Autowired
    public TemplateServiceImpl(TemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
    }

    public Page findAll(Pageable pageable) {
        return this.templateRepository.findAll(pageable);
    }

    public Template findById(Long id) throws ResourceNotFoundException {
        return null;
    }

    public Template save(Template template) throws DuplicateResourceException {
        return null;
    }

    public Template update(Template template) throws ResourceNotFoundException {
        return null;
    }

    public void delete(Long id) throws ResourceNotFoundException {

    }

}
