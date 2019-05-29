package com.salesplay.content.service.service;

import com.salesplay.content.service.domain.Guide;
import com.salesplay.content.service.exception.ResourceNotFoundException;
import com.salesplay.content.service.repository.GuideRepository;
import com.salesplay.content.service.repository.SiteLocaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GuideDatabaseService implements GuideService {

    private GuideRepository repository;

    @Autowired
    public GuideDatabaseService(GuideRepository repository, SiteLocaleRepository siteLocaleRepository) {
        this.repository = repository;
    }

    public Guide findBySlug(String slug) throws ResourceNotFoundException {
        return repository.findBySlug(slug).orElseThrow(()
                -> new ResourceNotFoundException(slug));
    }

    public Iterable<Guide> saveAll(Iterable<Guide> guides) {
        return repository.saveAll(guides);
    }

    public Guide save(Guide guide) {
        return repository.save(guide);
    }

    public Page<Guide> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Iterable findAllById(Iterable ids) {
        return repository.findAllById(ids);
    }

    public Guide findById(Long id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException(id.toString()));
    }

    public void deleteById(Long id) throws ResourceNotFoundException {
        repository.deleteById(findById(id).getId());
    }

    public void delete(Guide guide) throws ResourceNotFoundException {
        repository.delete(findById(guide.getId()));
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public void deleteAll(Iterable<Guide> guides) {
        repository.deleteAll(guides);
    }
}
