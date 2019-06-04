package com.auth.service.service;

import com.auth.service.entity.Privilege;
import com.auth.service.exception.DuplicateResourceException;
import com.auth.service.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PrivilegeService {

    Page findAll(Pageable pageable);

    Privilege findById(Long id) throws ResourceNotFoundException;

    Privilege save(Privilege privilege) throws DuplicateResourceException;

    Privilege update(Privilege privilege) throws ResourceNotFoundException;

    void delete(Long id) throws ResourceNotFoundException;

}
