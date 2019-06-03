package com.salesplay.content.service.service;

import com.salesplay.content.service.domain.SiteLocale;
import com.salesplay.content.service.exception.DuplicateResourceException;
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

    public SiteLocale create(SiteLocale siteLocale) throws DuplicateResourceException {

        if (repository.existsById(siteLocale.getId())) {
            throw new DuplicateResourceException(siteLocale.getId().toString());
        }

        return repository.save(siteLocale);
    }

    public SiteLocale update(SiteLocale siteLocale) throws ResourceNotFoundException {
        if (!repository.existsById(siteLocale.getId())) {
            throw new ResourceNotFoundException(siteLocale.getId().toString());
        }

        return repository.save(siteLocale);
    }

    public Page<SiteLocale> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Iterable<Long> findAllById(Iterable ids) {
        return repository.findAllById(ids);
    }


    public SiteLocale findById(Long id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException(id.toString()));
    }

    public void deleteById(Long id) throws ResourceNotFoundException {
        SiteLocale locale = repository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException(id.toString()));
        repository.deleteById(locale.getId());
    }

    public void delete(SiteLocale siteLocale) throws ResourceNotFoundException {
        SiteLocale locale = repository.findById(siteLocale.getId()).orElseThrow(()
                -> new ResourceNotFoundException(siteLocale.getId().toString()));
        repository.delete(locale);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public void deleteAll(Iterable<SiteLocale> locales) {
        repository.deleteAll(locales);
    }

}
