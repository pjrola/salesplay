package com.auth.service.controller;

import com.auth.service.dto.RolePrivilegesDTO;
import com.auth.service.entity.Role;
import com.auth.service.exception.DuplicateResourceException;
import com.auth.service.exception.ResourceNotFoundException;
import com.auth.service.service.RoleServiceImpl;
import com.auth.service.util.DTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
public class RoleController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private RoleServiceImpl roleService;

    @Autowired
    public RoleController(RoleServiceImpl roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/accounts/roles")
    public Page<Role> findAll(Pageable pageable) {
        log.info("findAll called with roles");
        return roleService.findAll(pageable);
    }

    @PostMapping("/accounts/roles")
    @ResponseStatus(HttpStatus.CREATED)
    public Role create(@Valid @RequestBody Role role) throws DuplicateResourceException {
        log.info("create called with role {}", role);
        return roleService.save(role);
    }

    @PutMapping("accounts/roles")
    @ResponseStatus(HttpStatus.OK)
    public Role update(@Valid @RequestBody Role role) throws ResourceNotFoundException {
        log.info("update called with role {}", role);
        return roleService.update(role);
    }

    @DeleteMapping("/accounts/roles/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable(value = "id") Long roleId) throws ResourceNotFoundException {
        log.info("delete called with role {}", roleId);
        roleService.delete(roleId);
    }

    @PostMapping("/accounts/roles/assignPrivileges")
    @ResponseStatus(HttpStatus.OK)
    public Role assignPrivileges(@DTO(RolePrivilegesDTO.class) Role role) throws ResourceNotFoundException {
        log.info("assignPrivileges called with role {}", role);
        return roleService.assignPrivilege(role);
    }

    @PostMapping("/accounts/roles/removePrivileges")
    @ResponseStatus(HttpStatus.OK)
    public Role removePrivileges(@DTO(RolePrivilegesDTO.class) Role role) throws ResourceNotFoundException {
        log.info("removePrivileges called with role {}", role);
        return roleService.removePrivilege(role);
    }

}
