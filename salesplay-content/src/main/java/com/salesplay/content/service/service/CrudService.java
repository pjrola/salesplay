package com.salesplay.content.service.service;

import com.salesplay.content.service.exception.DuplicateResourceException;
import com.salesplay.content.service.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CrudService<T, ID> {
    Iterable<T> saveAll(Iterable<T> types);

    T save(T type) throws DuplicateResourceException;

    Page<T> findAll(Pageable pageable);

    Iterable findAllById(Iterable ids);

    T findById(Long id) throws ResourceNotFoundException;

    void deleteById(Long id) throws ResourceNotFoundException;

    void delete(T type) throws ResourceNotFoundException;

    void deleteAll();

    void deleteAll(Iterable<T> types);

    T update(T type) throws ResourceNotFoundException;

    Long count();

    Boolean existsById(Long id) throws ResourceNotFoundException;
}
