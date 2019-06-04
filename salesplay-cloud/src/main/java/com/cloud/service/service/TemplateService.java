package com.cloud.service.service;

import com.cloud.service.entity.Template;
import com.cloud.service.exception.DuplicateResourceException;
import com.cloud.service.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TemplateService {

    Page findAll(Pageable pageable);

    Template findById(Long id) throws ResourceNotFoundException;

    Template save(Template template) throws DuplicateResourceException;

    Template update(Template template) throws ResourceNotFoundException;

    void delete(Long id) throws ResourceNotFoundException;

}