package com.salesplay.content.service.domain;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@SpringBootTest
public class GuideTest {

    SiteLocale en = SiteLocale.of("English", "en", true, true);
    SiteLocale de = SiteLocale.of("German", "de", false, true);
    SiteLocale fr = SiteLocale.of("French", "fr", false, true);

    private Guide guide = Guide.of("slug", EditorialStatus.PUBLISHED, Visibility.PUBLIC, "image");
    private GuideTranslation englishTranslation = GuideTranslation.of(en, "title", "subtitle", "overview");
    private GuideTranslation germanTranslation = GuideTranslation.of(de, "title", "subtitle", "overview");
    private GuideTranslation frenchTranslation = GuideTranslation.of(fr, "title", "subtitle", "overview");

    @Test
    public void addTranslationToGuide() throws Exception {
        guide.addTranslation(englishTranslation);
        assertEquals(1, guide.getTranslations().size());
    }

    @Test
    public void createInstanceOfGuide() throws Exception {
        guide.addTranslation(englishTranslation);

        assertEquals("published", guide.getEditorialStatus().getValue());
        assertEquals("public", guide.getVisibility().getValue());
        assertEquals("image", guide.getImage());
        assertEquals("slug", guide.getSlug());
        assertEquals(1, guide.getTranslations().size());
        assertEquals("en", englishTranslation.getLocale().getCode());
        assertEquals("title", englishTranslation.getTitle());
        assertEquals("subtitle", englishTranslation.getSubtitle());
        assertEquals("overview", englishTranslation.getOverview());
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwExceptionWhenDuplicateTranslationIsAdded() throws Exception {
        guide.addTranslation(englishTranslation);
        guide.addTranslation(englishTranslation);
    }

    @Test
    public void addTranslationsToGuide() throws Exception {
        List<GuideTranslation> translations = new ArrayList<>();
        translations.add(englishTranslation);
        translations.add(germanTranslation);
        translations.add(frenchTranslation);

        guide.addTranslations(translations);
        assertEquals(3, guide.getTranslations().size());
    }

    @Test
    public void removeTranslationFromGuide() throws Exception {
        guide.addTranslation(englishTranslation);
        assertEquals(1, guide.getTranslations().size());

        guide.removeTranslation(englishTranslation);
        assertEquals(0, guide.getTranslations().size());
    }

    @Test
    public void removeTranslationsFromGuide() throws Exception {
        List<GuideTranslation> translations = new ArrayList<>();
        translations.add(englishTranslation);
        translations.add(germanTranslation);
        translations.add(frenchTranslation);

        guide.addTranslations(translations);
        assertEquals(3, guide.getTranslations().size());

        guide.removeTranslations(translations);
        assertEquals(0, guide.getTranslations().size());
    }

    @Test
    public void getTranslationByLocale() throws Exception {
        guide.addTranslation(englishTranslation);
        GuideTranslation t = guide.getTranslationByLocale(en.getCode());

        assertEquals(englishTranslation, t);
    }

    @Test
    public void returnNullWhenTranslationIsNotFound() throws Exception {
        guide.addTranslation(englishTranslation);
        assertNull(guide.getTranslationByLocale(de.getCode()));
    }

}
