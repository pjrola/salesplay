package com.salesplay.content.service.service;

import com.salesplay.content.service.domain.MessageResource;
import java.util.List;

public interface MessageByLocaleService extends CrudService<MessageResource, Long> {
    Boolean existsByKeyAndLocale(String key, String locale);
    List<MessageResource> findAllByLocale(String locale);
}