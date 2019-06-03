package com.salesplay.content.service.controller;

import com.salesplay.content.service.exception.DuplicateResourceException;
import com.salesplay.content.service.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CrudController<T> {
    T create(T entity) throws DuplicateResourceException;

    Page<T> findAll(Pageable pageable);

    T findById(Long id) throws ResourceNotFoundException;

    void deleteById(Long id) throws ResourceNotFoundException;

    T update(T entity) throws ResourceNotFoundException;
}
