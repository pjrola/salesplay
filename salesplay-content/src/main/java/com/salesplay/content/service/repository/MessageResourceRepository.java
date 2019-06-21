package com.salesplay.content.service.repository;

import com.salesplay.content.service.domain.MessageResource;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageResourceRepository extends PagingAndSortingRepository<MessageResource, Long> {
    MessageResource findByKeyAndLocale(String key, String locale);
    Boolean existsByKeyAndLocale(String key, String locale);
    List<MessageResource> findAllByLocale(String locale);
}