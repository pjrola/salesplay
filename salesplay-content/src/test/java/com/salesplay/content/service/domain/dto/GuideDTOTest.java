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
    }

    @Test
    public void mapFromDto() {
    }
}
