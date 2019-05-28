package com.salesplay.content.service.service;

import com.salesplay.content.service.domain.Guide;

public interface GuideService extends CrudService<Guide, Long> {
    Guide findBySlug(String slug);
}
