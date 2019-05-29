package com.salesplay.content.service.domain.dto;

import com.salesplay.content.service.domain.*;
import com.salesplay.content.service.dto.GuideDTO;
import com.salesplay.content.service.dto.GuideMapper;
import com.salesplay.content.service.service.SiteLocaleDatabaseService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.Optional;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GuideDTOTest {

    SiteLocale enLocale = SiteLocale.of("English", "en_US", true, true);

    @Mock
    private SiteLocaleDatabaseService service;

    @InjectMocks
    private GuideMapper mapper;

    private Guide guide = Guide.of("slug", EditorialStatus.PUBLISHED, Visibility.PUBLIC, "image");

    private GuideTranslation englishTranslation = GuideTranslation.of(enLocale, "title", "subtitle", "overview");

    private GuideDTO dto = GuideDTO.of(enLocale.getCode(), "title", "subtitle", "slug", EditorialStatus.PUBLISHED, Visibility.PUBLIC, "test.png", "overview");

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        guide.addTranslation(englishTranslation);
    }

    @Test
    public void mapToDto() {
        when(service.findByCode(enLocale.getCode())).thenReturn(Optional.of(enLocale));
        GuideDTO dto = mapper.mapToDto(guide);

        assertEquals(dto.getLocale(), enLocale.getCode());
        assertEquals(dto.getTitle(), englishTranslation.getTitle());
        assertEquals(dto.getSubtitle(), englishTranslation.getSubtitle());
        assertEquals(dto.getSlug(), guide.getSlug());
        assertEquals(dto.getEditorialStatus(), guide.getEditorialStatus());
        assertEquals(dto.getVisibility(), guide.getVisibility());
        assertEquals(dto.getImage(), guide.getImage());
        assertEquals(dto.getOverview(), englishTranslation.getOverview());
    }

    @Test
    public void mapFromDto() {
        when(service.findByCode(enLocale.getCode())).thenReturn(Optional.of(enLocale));
        Guide guide = mapper.mapFromDto(dto);

        assertEquals(dto.getLocale(), enLocale.getCode());
        assertEquals(dto.getTitle(), englishTranslation.getTitle());
        assertEquals(dto.getSubtitle(), englishTranslation.getSubtitle());
        assertEquals(dto.getSlug(), guide.getSlug());
        assertEquals(dto.getEditorialStatus(), guide.getEditorialStatus());
        assertEquals(dto.getVisibility(), guide.getVisibility());
        assertEquals(dto.getImage(), guide.getImage());
        assertEquals(dto.getOverview(), englishTranslation.getOverview());
    }
}
