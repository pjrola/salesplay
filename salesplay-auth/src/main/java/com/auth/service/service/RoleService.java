package com.auth.service.service;

import com.auth.service.entity.Role;
import com.auth.service.exception.DuplicateResourceException;
import com.auth.service.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoleService {

    Role save(Role role) throws DuplicateResourceException;

    Role update(Role role) throws ResourceNotFoundException;

    void delete(Long id) throws ResourceNotFoundException;

    Page<Role> findAll(Pageable pageable);

    Role findById(Long id) throws ResourceNotFoundException;

    Role assignPrivilege(Role role) throws ResourceNotFoundException;

    Role removePrivilege(Role role) throws ResourceNotFoundException;

}
