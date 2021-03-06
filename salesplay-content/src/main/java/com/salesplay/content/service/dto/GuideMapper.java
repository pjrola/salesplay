package com.salesplay.content.service.dto;

import com.salesplay.content.service.domain.Guide;
import com.salesplay.content.service.domain.GuideTranslation;
import com.salesplay.content.service.domain.SiteLocale;
import com.salesplay.content.service.service.SiteLocaleDatabaseService;
import org.apache.commons.lang.LocaleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import java.util.Locale;
import java.util.Optional;

public class GuideMapper implements DTOMapper<Guide, GuideDTO> {

    @Autowired
    private SiteLocaleDatabaseService service;

    public GuideDTO mapToDto(Guide guide) {
        GuideDTO dto = new GuideDTO();

        Locale locale = LocaleContextHolder.getLocale();
        Optional<SiteLocale> siteLocale = service.findByCode(locale.toString());

        if (!siteLocale.isPresent() || !siteLocale.get().getIsEnabled()) {
            siteLocale = Optional.ofNullable(service.findByIsDefaultTrue());
        }

        Optional<GuideTranslation> translation = Optional.ofNullable(guide.getTranslationByLocale(siteLocale.get().getCode()));

        if (translation.isPresent()) {
            dto.setTitle(translation.get().getTitle());
            dto.setSubtitle(translation.get().getSubtitle());
            dto.setOverview(translation.get().getOverview());
        }

        dto.setImage(guide.getImage());
        dto.setSlug(guide.getSlug());
        dto.setVisibility(guide.getVisibility());
        dto.setLocale(siteLocale.get().getCode());
        dto.setEditorialStatus(guide.getEditorialStatus());

        return dto;
    }

    public Guide mapFromDto(GuideDTO dto) {
        Optional<SiteLocale> siteLocale = service.findByCode(dto.getLocale());

        if (!siteLocale.isPresent() || !siteLocale.get().getIsEnabled()) {
            throw new IllegalArgumentException("Locale Not Found or Disabled");
        }

        Guide guide = Guide.of(dto.getSlug(), dto.getEditorialStatus(), dto.getVisibility(), dto.getImage());
        GuideTranslation translation = GuideTranslation.of(siteLocale.get(), dto.getTitle(), dto.getSubtitle(), dto.getOverview());
        guide.addTranslation(translation);

        return guide;
    }

}