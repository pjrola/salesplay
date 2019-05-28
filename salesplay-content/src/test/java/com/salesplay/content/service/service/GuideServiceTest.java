package com.salesplay.content.service.service;

import com.salesplay.content.service.domain.*;
import com.salesplay.content.service.repository.GuideRepository;
import com.salesplay.content.service.repository.SiteLocaleRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(SpringRunner.class)
public class GuideServiceTest {

    @Mock
    private GuideRepository guideRepository;

    @Mock
    private SiteLocaleRepository siteLocaleRepository;

    @Mock
    private GuideDatabaseService guideDatabaseService;

    private Guide guideMock;
    private SiteLocale localeMock;
    private List<GuideTranslation> translations = new ArrayList<>();
    private GuideTranslation translationMock;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        guideDatabaseService = new GuideDatabaseService(guideRepository, siteLocaleRepository);
        localeMock = SiteLocale.of("English", "en", true, true);
        translationMock = GuideTranslation.of(localeMock, "title", "subtitle", "overview");
        translations.add(translationMock);
        guideMock = Guide.of("my-slug", EditorialStatus.PUBLISHED, Visibility.PUBLIC, "test.png");
        guideMock.addTranslations(translations);
    }

    @Test
    public void canRetrievePageList() throws Exception {
        List<Guide> guideList = new ArrayList<>();
        guideList.add(guideMock);
        guideList.add(guideMock);
        guideList.add(guideMock);
        Page<Guide> pagedResponse = new PageImpl(guideList);

        when(guideRepository.findAll(PageRequest.of(0, 10))).thenReturn(pagedResponse);

        Page<Guide> result = guideDatabaseService.findAll(PageRequest.of(0, 10));
        assertTrue(result.hasContent());
        assertEquals(3, result.getTotalElements());
    }

    @Test
    public void canRetrieveById() throws Exception {
        when(guideRepository.findById(1L)).thenReturn(Optional.ofNullable(guideMock));
        Optional<Guide> result = Optional.ofNullable(guideDatabaseService.findById(1L));
        assertTrue(result.isPresent());
        assertEquals(result.get(), guideMock);
    }

    @Test
    public void canRetrieveBySlug() throws Exception {
        when(guideRepository.findBySlug(guideMock.getSlug())).thenReturn(Optional.of(guideMock));
        Optional<Guide> result = Optional.ofNullable(guideDatabaseService.findBySlug(guideMock.getSlug()));
        assertTrue(result.isPresent());
        assertEquals(guideMock, result.get());
    }

    @Test
    public void canDeleteByEntity() throws Exception {
        when(guideRepository.findById(1L)).thenReturn(Optional.ofNullable(guideMock));
        Optional<Guide> result = Optional.ofNullable(guideDatabaseService.findById(1L));
        guideDatabaseService.deleteById(1L);
        verify(guideRepository, times(1)).deleteById(result.get().getId());
    }

    @Test
    public void canSaveAllEntities() throws Exception {
        List<Guide> guideList = new ArrayList<>();
        guideList.add(guideMock);
        guideList.add(guideMock);
        guideList.add(guideMock);

        when(guideRepository.saveAll(guideList)).thenReturn(guideList);
        verify(guideRepository, times(1)).saveAll(guideList);
    }

    @Test
    public void canSaveEntity() throws Exception {
        when(guideRepository.save(guideMock)).thenReturn(guideMock);
        Guide result = guideDatabaseService.save(guideMock);
        assertEquals(guideMock, result);
        verify(guideRepository, times(1)).save(guideMock);
    }

    @Test
    public void canFindAllById() throws Exception {
        List<Long> idList = new ArrayList<>();
        idList.add(1L);
        idList.add(2L);
        idList.add(3L);

        List<Guide> guideList = new ArrayList<>();
        guideList.add(guideMock);
        guideList.add(guideMock);
        guideList.add(guideMock);
        when(guideRepository.findAllById(idList)).thenReturn(guideList);

        Iterable guides = guideDatabaseService.findAllById(idList);
        assertEquals(guideList, guides);
        verify(guideRepository, times(1)).findAllById(idList);
    }

    @Test
    public void deleteAll() throws Exception {
        when(guideRepository.deleteAll());
        Optional<Guide> result = Optional.ofNullable(guideDatabaseService.findById(1L));
        guideDatabaseService.deleteById(1L);
        verify(guideRepository, times(1)).deleteById(result.get().getId());
    }
}
