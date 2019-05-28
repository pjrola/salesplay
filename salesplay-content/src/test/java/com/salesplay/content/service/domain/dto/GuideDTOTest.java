package com.salesplay.content.service.domain.dto;

import com.salesplay.content.service.domain.*;
import com.salesplay.content.service.dto.GuideDTO;
import com.salesplay.content.service.dto.GuideMapper;
import org.junit.Before;
import org.junit.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.context.SpringBootTest;
import static junit.framework.TestCase.assertEquals;

@SpringBootTest
public class GuideDTOTest {

    SiteLocale en = SiteLocale.of("English", "en", true, true);
    SiteLocale de = SiteLocale.of("German", "de", false, true);
    SiteLocale fr = SiteLocale.of("French", "fr", false, true);

    private final static GuideMapper mapper = Mappers.getMapper(GuideMapper.class);

    private Guide guide = Guide.of("slug", EditorialStatus.PUBLISHED, Visibility.PUBLIC, "image");

    private GuideTranslation englishTranslation = GuideTranslation.of(en, "title", "subtitle", "overview");
    private GuideTranslation germanTranslation = GuideTranslation.of(de, "title", "subtitle", "overview");
    private GuideTranslation frenchTranslation = GuideTranslation.of(fr, "title", "subtitle", "overview");

    @Before
    public void setup() {
        guide.addTranslation(englishTranslation);
        guide.addTranslation(germanTranslation);
        guide.addTranslation(frenchTranslation);
    }

    @Test
    public void mapToDto() {
        GuideDTO dto = mapper.INSTANCE.mapToDto(guide, guide.getTranslationByLocale(en));
        assertEquals(dto.getTitle(), guide.getTranslationByLocale(en).getTitle());
        assertEquals(dto.getSubtitle(), guide.getTranslationByLocale(en).getSubtitle());
        assertEquals(dto.getOverview(), guide.getTranslationByLocale(en).getOverview());
        assertEquals(dto.getImage(), guide.getImage());
        assertEquals(dto.getEditorialStatus(), guide.getEditorialStatus());
        assertEquals(dto.getVisibility(), guide.getVisibility());
        assertEquals(dto.getSlug(), guide.getSlug());
        assertEquals(dto.getLocale(), guide.getTranslationByLocale(en).getLocale().getCode());
    }

    @Test
    public void mapFromDto() {
        GuideDTO dto = mapper.INSTANCE.mapToDto(guide, guide.getTranslationByLocale(en));
        Guide entity = mapper.INSTANCE.mapFromDto(dto);
        assertEquals(dto.getTitle(), entity.getTranslationByLocale(en).getTitle());
        assertEquals(dto.getSubtitle(), entity.getTranslationByLocale(en).getSubtitle());
        assertEquals(dto.getOverview(), entity.getTranslationByLocale(en).getOverview());
        assertEquals(dto.getImage(), entity.getImage());
        assertEquals(dto.getEditorialStatus(), entity.getEditorialStatus());
        assertEquals(dto.getVisibility(), entity.getVisibility());
        assertEquals(dto.getSlug(), entity.getSlug());
        assertEquals(dto.getLocale(), entity.getTranslationByLocale(en).getLocale().getCode());
    }
}
