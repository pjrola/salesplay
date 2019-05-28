package com.salesplay.content.service.repository;

import com.salesplay.content.service.domain.Guide;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GuideRepository extends PagingAndSortingRepository<Guide, Long> {
    Optional<Guide> findBySlug(String slug);
}
