package com.auth.service.controller;

import com.auth.service.entity.Privilege;
import com.auth.service.exception.DuplicateResourceException;
import com.auth.service.exception.ResourceNotFoundException;
import com.auth.service.service.PrivilegeServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
public class PrivilegeController {

    private PrivilegeServiceImpl privilegeService;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public PrivilegeController(PrivilegeServiceImpl privilegeService) {
        this.privilegeService = privilegeService;
    }

    @GetMapping("/accounts/privileges")
    public Page<Privilege> findAll(Pageable pageable) {
        log.info("findAll called with privilege");
        return privilegeService.findAll(pageable);
    }

    @PostMapping("/accounts/privileges")
    public ResponseEntity<Privilege> create(@Valid @RequestBody Privilege privilege) throws DuplicateResourceException {
        log.info("create called with privilege {}", privilege);
        return new ResponseEntity<>(privilegeService.save(privilege), HttpStatus.CREATED);
    }

    @PutMapping("/accounts/privileges")
    public ResponseEntity<Privilege> update(@Valid @RequestBody Privilege privilege) throws ResourceNotFoundException {
        log.info("update called with privilege {}", privilege);
        return new ResponseEntity<>(privilegeService.update(privilege), HttpStatus.OK);
    }

    @DeleteMapping("/accounts/privileges/{id}")
    public ResponseEntity delete(@PathVariable(value = "id") Long privilegeId) throws ResourceNotFoundException {
        log.info("delete called with privilege {}", privilegeId);
        privilegeService.delete(privilegeId);
        return ResponseEntity.ok().build();
    }

}
