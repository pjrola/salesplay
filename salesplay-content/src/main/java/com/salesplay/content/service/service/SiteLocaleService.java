package com.salesplay.content.service.service;

import com.salesplay.content.service.domain.SiteLocale;

public interface SiteLocaleService extends CrudService<SiteLocale, Long> {
    String getMessage(String code);
    String getMessageWithParam(String code, Object[] var2);
}
