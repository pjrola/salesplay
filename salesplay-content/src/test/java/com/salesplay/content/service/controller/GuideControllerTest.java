package com.salesplay.content.service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.salesplay.content.service.domain.*;
import com.salesplay.content.service.dto.GuideDTO;
import com.salesplay.content.service.dto.GuideMapper;
import com.salesplay.content.service.exception.ResourceNotFoundException;
import com.salesplay.content.service.service.GuideDatabaseService;
import com.salesplay.content.service.service.MessageByLocaleDatabaseService;
import com.salesplay.content.service.service.SiteLocaleDatabaseService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import java.util.*;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(GuideController.class)
@EnableSpringDataWebSupport
@EnableWebMvc
public class GuideControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GuideDatabaseService guideService;

    @MockBean
    private MessageByLocaleDatabaseService message;

    @MockBean
    private SiteLocaleDatabaseService siteLocaleDatabaseService;

    @Autowired
    private GuideMapper guideMapper;

    @Autowired
    private ObjectMapper mapper;

    private SiteLocale enLocale;

    private Guide guideMock;

    private List<GuideTranslation> translations = new ArrayList<>();

    private GuideTranslation translationMock;

    @Before
    public void setUp() throws Exception {
        enLocale = SiteLocale.of("English", "en_US", true, true);
        translationMock = GuideTranslation.of(enLocale, "title", "subtitle", "overview");
        guideMock = Guide.of("my-slug", EditorialStatus.PUBLISHED, Visibility.PUBLIC, "test.png");
        translations.add(translationMock);
        guideMock.addTranslations(translations);
    }

    @Test
    public void canRetrieveListOfGuides() throws Exception {
        List<Guide> guides = new ArrayList<>();
        guides.add(guideMock);
        guides.add(guideMock);
        guides.add(guideMock);

        Page<Guide> pagedResponse = new PageImpl(guides);

        when(guideService.findAll(PageRequest.of(0, 20))).thenReturn(pagedResponse);
        when(siteLocaleDatabaseService.findByCode(enLocale.getCode())).thenReturn(Optional.of(enLocale));

        mockMvc.perform(get("/guides"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))

                .andExpect(jsonPath("$.content", hasSize(3)))
                .andExpect(jsonPath("$.content[0].locale", is(enLocale.getCode())))
                .andExpect(jsonPath("$.content[0].title", is(translationMock.getTitle())))
                .andExpect(jsonPath("$.content[0].subtitle", is(translationMock.getSubtitle())))
                .andExpect(jsonPath("$.content[0].overview", is(translationMock.getOverview())))
                .andExpect(jsonPath("$.content[0].editorialStatus", is(guideMock.getEditorialStatus().getValue())))
                .andExpect(jsonPath("$.content[0].image", is(guideMock.getImage())))
                .andExpect(jsonPath("$.content[0].slug", is(guideMock.getSlug())))
                .andExpect(jsonPath("$.content[0].visibility", is(guideMock.getVisibility().getValue())))

                .andExpect(jsonPath("$.content[1].locale", is(enLocale.getCode())))
                .andExpect(jsonPath("$.content[1].title", is(translationMock.getTitle())))
                .andExpect(jsonPath("$.content[1].subtitle", is(translationMock.getSubtitle())))
                .andExpect(jsonPath("$.content[1].overview", is(translationMock.getOverview())))
                .andExpect(jsonPath("$.content[1].editorialStatus", is(guideMock.getEditorialStatus().getValue())))
                .andExpect(jsonPath("$.content[1].image", is(guideMock.getImage())))
                .andExpect(jsonPath("$.content[1].slug", is(guideMock.getSlug())))
                .andExpect(jsonPath("$.content[1].visibility", is(guideMock.getVisibility().getValue())))

                .andExpect(jsonPath("$.content[2].locale", is(enLocale.getCode())))
                .andExpect(jsonPath("$.content[2].title", is(translationMock.getTitle())))
                .andExpect(jsonPath("$.content[2].subtitle", is(translationMock.getSubtitle())))
                .andExpect(jsonPath("$.content[2].overview", is(translationMock.getOverview())))
                .andExpect(jsonPath("$.content[2].editorialStatus", is(guideMock.getEditorialStatus().getValue())))
                .andExpect(jsonPath("$.content[2].image", is(guideMock.getImage())))
                .andExpect(jsonPath("$.content[2].slug", is(guideMock.getSlug())))
                .andExpect(jsonPath("$.content[2].visibility", is(guideMock.getVisibility().getValue())));
    }

    @Test
    public void canRetrieveGuideByIdWhenExists() throws Exception {
        when(guideService.findById(1L)).thenReturn(guideMock);
        when(siteLocaleDatabaseService.findByCode(enLocale.getCode())).thenReturn(Optional.of(enLocale));

        String actual = mapper.writeValueAsString(guideMapper.mapToDto(guideMock));

        ResultActions resultActions = mockMvc.perform(get("/guides/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        JSONAssert.assertEquals(
                contentAsString, actual,
                JSONCompareMode.LENIENT
        );
    }

    @Test
    public void canRetrieveGuideBySlugWhenExists() throws Exception {
        when(guideService.findBySlug(guideMock.getSlug())).thenReturn(guideMock);

        String actual = mapper.writeValueAsString(guideMock);

        ResultActions resultActions = mockMvc.perform(get("/guides/slug/{slug}", guideMock.getSlug()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        JSONAssert.assertEquals(
                contentAsString, actual,
                JSONCompareMode.LENIENT
        );
    }

    @Test
    public void shouldThrowResourceNotFoundExceptionWhenFindByIdGuideDoesNotExist() throws Exception {
        when(guideService.findById(1L)).thenThrow(new ResourceNotFoundException("1"));

        mockMvc.perform(get("/guides/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldThrowResourceNotFoundExceptionWhenFindBySlugGuideDoesNotExist() throws Exception {
        when(guideService.findBySlug(guideMock.getSlug())).thenThrow(new ResourceNotFoundException(guideMock.getSlug()));

        mockMvc.perform(get("/guides/slug/{slug}", guideMock.getSlug()))
                .andExpect(status().isNotFound());
    }

    @Test
    public void canUpdateAGuideWithValidInput() throws Exception {
        when(guideService.update(guideMock)).thenReturn(guideMock);
        String actual = mapper.writeValueAsString(guideMock);

        ResultActions resultActions = mockMvc.perform(put("/guides").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(actual))
                .andExpect(status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        JSONAssert.assertEquals(
                contentAsString, actual,
                JSONCompareMode.LENIENT
        );
    }

    @Test
    public void canBulkDeleteAGuideWithValidId() throws Exception {
        List<Guide> guides = new LinkedList<>();
        guides.add(guideMock);

        mockMvc.perform(post("/guides/delete").contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(guides))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void canPostGuideWithValidInput() throws Exception {
        when(guideService.create(guideMock)).thenReturn(guideMock);
        when(siteLocaleDatabaseService.findByCode(enLocale.getCode())).thenReturn(Optional.of(enLocale));

        GuideDTO guideDTO = guideMapper.mapToDto(guideMock);

        String actual = mapper.writeValueAsString(guideDTO);

        ResultActions resultActions = mockMvc.perform(post("/guides")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(actual))
                .andExpect(status().isCreated());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        JSONAssert.assertEquals(
            contentAsString, actual,
            JSONCompareMode.LENIENT
        );
    }

    @Test
    public void shouldRejectRequestWhenGuideCreatingGuideWithInvalidInput() throws Exception {
        String actual = "{name:\"Test\"}";
        mockMvc.perform(post("/guides")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(actual))
                .andExpect(status().isBadRequest());
    }

}