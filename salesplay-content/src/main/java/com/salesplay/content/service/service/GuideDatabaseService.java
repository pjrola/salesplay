package com.salesplay.content.service.service;

import com.salesplay.content.service.domain.Guide;
import com.salesplay.content.service.repository.GuideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.validation.constraints.NotNull;

@Service
public class GuideDatabaseService {

    @NotNull
    private GuideRepository repository;

    @Autowired
    public GuideDatabaseService(GuideRepository repository) {
        this.repository = repository;
    }

    public Page<Guide> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
