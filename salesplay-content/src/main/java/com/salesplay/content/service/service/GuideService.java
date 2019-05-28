package com.salesplay.content.service.service;

import com.salesplay.content.service.domain.Guide;
import com.salesplay.content.service.exception.ResourceNotFoundException;

public interface GuideService extends CrudService<Guide, Long> {
    Guide findBySlug(String slug) throws ResourceNotFoundException;
}
