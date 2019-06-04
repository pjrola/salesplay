package com.salesplay.content.service.service;

import com.salesplay.content.service.domain.SiteLocale;
import java.util.List;
import java.util.Optional;

public interface SiteLocaleService extends CrudService<SiteLocale, Long> {
    Optional<SiteLocale> findByCode(String code);
    SiteLocale findByIsDefaultTrue();
    List<SiteLocale> findAllByIsEnabledTrue();
}
