package com.salesplay.content.service.repository;

import com.salesplay.content.service.domain.SiteLocale;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SiteLocaleRepository extends PagingAndSortingRepository<SiteLocale, Long> {
    Optional<SiteLocale> findByCode(String code);
    SiteLocale findByIsDefaultTrue();
    List<SiteLocale> findAllByIsEnabledTrue();
}
