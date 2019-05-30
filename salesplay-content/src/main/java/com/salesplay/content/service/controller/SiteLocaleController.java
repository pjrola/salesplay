package com.salesplay.content.service.controller;

import com.salesplay.content.service.domain.SiteLocale;
import com.salesplay.content.service.exception.DuplicateResourceException;
import com.salesplay.content.service.exception.ResourceNotFoundException;
import com.salesplay.content.service.service.SiteLocaleDatabaseService;
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
public class SiteLocaleController {

    private static final String RESOURCE_PATH = "/locales";

    @NotNull
    private SiteLocaleDatabaseService service;

    @Autowired
    public SiteLocaleController(SiteLocaleDatabaseService service) {
        this.service = service;
    }

    @GetMapping(RESOURCE_PATH)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Page<SiteLocale> findAll(Pageable pageable) {
        return service.findAll(pageable);
    }

    @PostMapping(RESOURCE_PATH)
    @ResponseBody
    public ResponseEntity<SiteLocale> create(@Valid @RequestBody SiteLocale locale) throws DuplicateResourceException {
        return new ResponseEntity<>(service.save(locale), HttpStatus.CREATED);
    }

    @PostMapping(RESOURCE_PATH + "/delete")
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestBody List<SiteLocale> locales) throws ResourceNotFoundException {
        service.deleteAll(locales);
    }

    @PutMapping(RESOURCE_PATH)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public SiteLocale update(@RequestBody SiteLocale locale) throws ResourceNotFoundException {
        return service.save(locale);
    }

    @GetMapping(RESOURCE_PATH + "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public SiteLocale findById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        return service.findById(id);
    }
}
