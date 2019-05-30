package com.salesplay.content.service.dto;

import com.salesplay.content.service.domain.Guide;
import com.salesplay.content.service.domain.GuideTranslation;
import com.salesplay.content.service.domain.SiteLocale;
import com.salesplay.content.service.service.SiteLocaleDatabaseService;
import org.apache.commons.lang.LocaleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import java.util.Locale;
import java.util.Optional;

@Component
public class GuideMapper implements DTOMapper<Guide, GuideDTO> {

    private SiteLocaleDatabaseService service;

    private GuideDTO dto = new GuideDTO();

    @Autowired
    public GuideMapper(SiteLocaleDatabaseService service) {
        this.service = service;
    }

    public GuideDTO mapToDto(Guide guide) {
        Locale locale = LocaleContextHolder.getLocale();

        if (!LocaleUtils.isAvailableLocale(locale)) {
            throw new IllegalArgumentException("Invalid Locale");
        }

        Optional<SiteLocale> siteLocale = service.findByCode(locale.toString());
        Optional<GuideTranslation> translation = Optional.ofNullable(guide.getTranslationByLocale(locale.toString()));

        if (!siteLocale.isPresent() || !siteLocale.get().getIsEnabled()) {
            throw new IllegalArgumentException("Locale Not Found or Disabled");
        }

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