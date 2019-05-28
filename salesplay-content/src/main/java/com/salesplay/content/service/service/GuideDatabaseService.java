package com.salesplay.content.service.service;

import com.salesplay.content.service.domain.Guide;
import com.salesplay.content.service.repository.GuideRepository;
import com.salesplay.content.service.repository.SiteLocaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Service
public class GuideDatabaseService implements CrudService<Guide, Long> {

    @Autowired
    HttpServletRequest request;

    @NotNull
    private GuideRepository repository;

    @NotNull
    private SiteLocaleRepository localeRepository;

    @Autowired
    public GuideDatabaseService(GuideRepository repository, SiteLocaleRepository localeRepository) {
        this.repository = repository;
        this.localeRepository = localeRepository;
    }

    public Guide save(Guide entity) {
        return null;
    }

    public Guide update(Guide entity) {
        return null;
    }

    public Optional<Guide> findById(Long id) {
        return Optional.empty();
    }

    public boolean existsById(Long id) {
        return false;
    }

    public Page<Guide> findAll(Pageable pageable) {
        return null;
    }

    public long count() {
        return 0;
    }

    public void deleteById(Long id) {

    }

    public void delete(Guide entity) {

    }

    public void deleteAll() {

    }
}
