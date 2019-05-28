package com.salesplay.content.service.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface CrudService<T, ID> {
    T save(T entity);

    T update(T entity);

    Optional<T> findById(ID id);

    boolean existsById(ID id);

    Page<T> findAll(Pageable pageable);

    long count();

    void deleteById(ID id);

    void delete(T entity);

    void deleteAll();
}
