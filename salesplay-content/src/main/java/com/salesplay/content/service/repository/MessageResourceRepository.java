package com.salesplay.content.service.repository;

import com.salesplay.content.service.domain.MessageResource;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageResourceRepository extends PagingAndSortingRepository<MessageResource, Long> {
    MessageResource findByKeyAndLocale(String key, String locale);
}