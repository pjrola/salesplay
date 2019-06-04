package com.auth.service.service;

import com.auth.service.entity.Role;
import com.auth.service.exception.DuplicateResourceException;
import com.auth.service.exception.ResourceNotFoundException;
import com.auth.service.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private RoleRepository roleRepository;
    private String roleNotFound;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, MessageByLocaleService messageByLocaleService) {
        this.roleRepository = roleRepository;
        this.roleNotFound = messageByLocaleService.getMessage("account.role.NotFound");
    }

    public Role save(Role role) throws DuplicateResourceException {
        log.debug("saveRole called");
        Optional<Role> duplicateRole = roleRepository.findByName(role.getName());

        if (duplicateRole.isPresent()) {
            throw DuplicateResourceException.createWith(role.getName());
        }

        return roleRepository.save(role);
    }

    public Role update(Role role) throws ResourceNotFoundException {
        log.debug("updateRole called");
        roleRepository.findById(role.getId()).orElseThrow(() -> new ResourceNotFoundException(role.getId(), roleNotFound));

        return roleRepository.save(role);
    }

    public void delete(Long id) throws ResourceNotFoundException {
        log.debug("deleteRole called");
        Role roleToUpdate = roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id, roleNotFound));

        roleRepository.delete(roleToUpdate);
    }

    public Page<Role> findAll(Pageable pageable) {
        log.debug("getAllRoles called");
        return roleRepository.findAll(pageable);
    }

    public Role findById(Long id) throws ResourceNotFoundException {
        log.debug("findById called");
        return roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id, roleNotFound));
    }

    public Role assignPrivilege(Role role) throws ResourceNotFoundException {
        Role existingRole = roleRepository.findByName(role.getName()).orElseThrow(()
                -> new ResourceNotFoundException(role.getId(), "role not found"));

        existingRole.addPrivileges(role.getPrivileges());

        return roleRepository.save(existingRole);
    }

    public Role removePrivilege(Role role) throws ResourceNotFoundException {
        Role existingRole = roleRepository.findByName(role.getName()).orElseThrow(()
                -> new ResourceNotFoundException(role.getId(), "role not found"));

        existingRole.removePrivileges(role.getPrivileges());

        return roleRepository.save(existingRole);
    }
}
