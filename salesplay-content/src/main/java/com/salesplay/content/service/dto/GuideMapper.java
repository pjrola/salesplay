package com.salesplay.content.service.dto;

import com.salesplay.content.service.domain.Guide;
import com.salesplay.content.service.domain.GuideTranslation;
import com.salesplay.content.service.domain.SiteLocale;
import com.salesplay.content.service.repository.SiteLocaleRepository;
import org.apache.commons.lang.LocaleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import java.util.Locale;
import java.util.Optional;

@Component
public class GuideMapper {

    private SiteLocaleRepository localeRepository;

    @Autowired
    public GuideMapper(SiteLocaleRepository localeRepository) {
        this.localeRepository = localeRepository;
    }

    public GuideDTO mapToDto(Guide guide) {
        GuideDTO dto = new GuideDTO();
        Locale locale = LocaleContextHolder.getLocale();

        if (!LocaleUtils.isAvailableLocale(locale)) {
            throw new IllegalArgumentException("Invalid Locale Supplied");
        }

        Optional<SiteLocale> siteLocale = localeRepository.findByCode(locale.toString());
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
        dto.setLocale(locale.toString());
        dto.setEditorialStatus(guide.getEditorialStatus());

        return dto;
    }

    public Guide mapFromDto(GuideDTO dto) {
        return null;
    }

}