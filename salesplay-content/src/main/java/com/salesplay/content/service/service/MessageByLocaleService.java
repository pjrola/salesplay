package com.salesplay.content.service.service;

import com.salesplay.content.service.domain.MessageResource;

public interface MessageByLocaleService extends CrudService<MessageResource, Long> {
    String getMessage(String code);
    String getMessageWithParam(String code, Object[] var2);
    MessageResource findByKeyAndLocale(String key, String locale);
}