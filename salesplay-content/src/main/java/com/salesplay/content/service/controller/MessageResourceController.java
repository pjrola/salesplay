package com.salesplay.content.service.controller;

import com.salesplay.content.service.domain.MessageResource;
import com.salesplay.content.service.exception.DuplicateResourceException;
import com.salesplay.content.service.exception.ResourceNotFoundException;
import com.salesplay.content.service.service.MessageByLocaleDatabaseService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Log4j2
@RestController
public class MessageResourceController {

    private static final String RESOURCE_PATH = "/messageResources";

    @NotNull
    private MessageByLocaleDatabaseService service;

    @Autowired
    public MessageResourceController(MessageByLocaleDatabaseService service) {
        this.service = service;
    }

    @GetMapping(RESOURCE_PATH)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Page<MessageResource> findAll(Pageable pageable) {
        return service.findAll(pageable);
    }

    @PostMapping(RESOURCE_PATH)
    @ResponseBody
    public ResponseEntity<MessageResource> create(@Valid @RequestBody MessageResource messageResource) throws DuplicateResourceException {
        return new ResponseEntity<>(service.create(messageResource), HttpStatus.CREATED);
    }

    @PostMapping(RESOURCE_PATH + "/delete")
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestBody List<MessageResource> messageResources) {
        service.deleteAll(messageResources);
    }

    @PutMapping(RESOURCE_PATH)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public MessageResource update(@RequestBody MessageResource messageResource) throws ResourceNotFoundException {
        return service.update(messageResource);
    }

    @GetMapping(RESOURCE_PATH + "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public MessageResource findById(@PathVariable(value = "id") Long translationId) throws ResourceNotFoundException {
        return service.findById(translationId);
    }

}
