package com.salesplay.content.service.service;

import com.salesplay.content.service.domain.Guide;
import com.salesplay.content.service.exception.DuplicateResourceException;
import com.salesplay.content.service.exception.ResourceNotFoundException;
import com.salesplay.content.service.repository.GuideRepository;
import com.salesplay.content.service.repository.SiteLocaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;

@Service
public class GuideDatabaseService implements GuideService {

    @Autowired
    HttpServletRequest request;

    private GuideRepository repository;

    private SiteLocaleRepository siteLocaleRepository;

    @Autowired
    public GuideDatabaseService(GuideRepository repository, SiteLocaleRepository siteLocaleRepository) {
        this.repository = repository;
        this.siteLocaleRepository = siteLocaleRepository;
    }

    public Guide findBySlug(String slug) throws ResourceNotFoundException {
        return repository.findBySlug(slug).orElseThrow(()
                -> new ResourceNotFoundException(slug));
    }

    public Iterable<Guide> saveAll(Iterable<Guide> types) {
        return null;
    }

    public Guide save(Guide type) throws DuplicateResourceException {
        return null;
    }

    public Page<Guide> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Iterable findAllById(Iterable ids) {
        return null;
    }

    public Guide findById(Long id) throws ResourceNotFoundException {
        return null;
    }

    public void deleteById(Long id) throws ResourceNotFoundException {

    }

    public void delete(Guide type) throws ResourceNotFoundException {

    }

    public void deleteAll() {

    }

    public void deleteAll(Iterable<Guide> types) {

    }

    public Guide update(Guide type) throws ResourceNotFoundException {
        return null;
    }

    public Long count() {
        return null;
    }

    public Boolean existsById(Long id) throws ResourceNotFoundException {
        return null;
    }
}
