package com.auth.service.service;

import com.auth.service.entity.Privilege;
import com.auth.service.exception.DuplicateResourceException;
import com.auth.service.exception.ResourceNotFoundException;
import com.auth.service.repository.PrivilegeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class PrivilegeServiceImpl implements PrivilegeService {

    private PrivilegeRepository privilegeRepository;

    private String privilegeNotFound;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public PrivilegeServiceImpl(PrivilegeRepository privilegeRepository, MessageByLocaleServiceImpl messageService) {
        this.privilegeRepository = privilegeRepository;
        this.privilegeNotFound = messageService.getMessage("account.privilege.notFound");
    }

    public Page findAll(Pageable pageable) {
        log.debug("findAll called");
        return privilegeRepository.findAll(pageable);
    }

    public Privilege findById(Long id) throws ResourceNotFoundException {
        log.debug("findById called");
        return privilegeRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException(id, privilegeNotFound));
    }

    public Privilege save(Privilege privilege) throws DuplicateResourceException {
        log.debug("savePrivilege called");
        Optional<Privilege> existingPrivilege = privilegeRepository.findByName(privilege.getName());

        if (existingPrivilege.isPresent()) {
            throw DuplicateResourceException.createWith(privilege.getName());
        }

        return privilegeRepository.save(privilege);
    }

    public Privilege update(Privilege privilege) throws ResourceNotFoundException {
        log.debug("updatePrivilege called");
        Privilege existingPrivilege = privilegeRepository.findById(privilege.getId()).orElseThrow(()
                -> new ResourceNotFoundException(privilege.getId(), privilegeNotFound));

        existingPrivilege.setName(privilege.getName());
        existingPrivilege.setDescription(privilege.getDescription());
        existingPrivilege.setRoles(privilege.getRoles());

        return privilegeRepository.save(existingPrivilege);
    }

    public void delete(Long id) throws ResourceNotFoundException {
        Privilege existingPrivilege = privilegeRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException(id, privilegeNotFound));
        log.debug("deletePrivilege called");

        privilegeRepository.delete(existingPrivilege);

    }
}
