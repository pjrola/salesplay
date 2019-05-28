package com.salesplay.content.service.service;

import com.salesplay.content.service.domain.MessageResource;

public interface MessageResourceService extends CrudService<MessageResource, Long> {
    MessageResource findByKeyAndLocale(String key, String locale);
}
