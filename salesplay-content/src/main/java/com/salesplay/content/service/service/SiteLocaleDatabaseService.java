package com.salesplay.content.service.service;

import com.salesplay.content.service.domain.SiteLocale;
import com.salesplay.content.service.exception.DuplicateResourceException;
import com.salesplay.content.service.exception.ResourceNotFoundException;
import com.salesplay.content.service.repository.SiteLocaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SiteLocaleDatabaseService implements SiteLocaleService {

    private SiteLocaleRepository siteLocaleRepository;

    @Autowired
    public SiteLocaleDatabaseService(SiteLocaleRepository siteLocaleRepository) {
        this.siteLocaleRepository = siteLocaleRepository;
    }

    public String getMessage(String code) {
        return null;
    }

    public String getMessageWithParam(String code, Object[] var2) {
        return null;
    }

    public Iterable<SiteLocale> saveAll(Iterable<SiteLocale> types) {
        return null;
    }

    public SiteLocale save(SiteLocale type) throws DuplicateResourceException {
        return null;
    }

    public Page<SiteLocale> findAll(Pageable pageable) {
        return null;
    }

    public Iterable findAllById(Iterable ids) {
        return null;
    }

    public SiteLocale findById(Long id) throws ResourceNotFoundException {
        return null;
    }

    public void deleteById(Long id) throws ResourceNotFoundException {

    }

    public void delete(SiteLocale type) throws ResourceNotFoundException {

    }

    public void deleteAll() {

    }

    public void deleteAll(Iterable<SiteLocale> types) {

    }

    public SiteLocale update(SiteLocale type) throws ResourceNotFoundException {
        return null;
    }

    public Long count() {
        return null;
    }

    public Boolean existsById(Long id) throws ResourceNotFoundException {
        return null;
    }
}
