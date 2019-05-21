package com.salesplay.content.service.repository;

import com.salesplay.content.service.domain.Guide;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuideRepository extends PagingAndSortingRepository<Guide, Long> {
}
