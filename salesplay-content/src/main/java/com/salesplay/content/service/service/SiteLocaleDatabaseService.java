package com.salesplay.content.service.service;

import com.salesplay.content.service.domain.SiteLocale;
import com.salesplay.content.service.exception.ResourceNotFoundException;
import com.salesplay.content.service.repository.SiteLocaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class SiteLocaleDatabaseService implements SiteLocaleService {

    private SiteLocaleRepository repository;

    @Autowired
    public SiteLocaleDatabaseService(SiteLocaleRepository repository) {
        this.repository = repository;
    }

    public Optional<SiteLocale> findByCode(String code) {
        return repository.findByCode(code);
    }

    public SiteLocale findByIsDefaultTrue() {
        return repository.findByIsDefaultTrue();
    }

    public Iterable<SiteLocale> saveAll(Iterable<SiteLocale> locales) {
        return repository.saveAll(locales);
    }

    public SiteLocale save(SiteLocale locale) {
        return repository.save(locale);
    }

    public Page<SiteLocale> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Iterable findAllById(Iterable ids) {
        return repository.findAllById(ids);
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
}
