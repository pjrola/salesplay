package com.salesplay.content.service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.salesplay.content.service.domain.SiteLocale;
import com.salesplay.content.service.exception.DuplicateResourceException;
import com.salesplay.content.service.exception.ResourceNotFoundException;
import com.salesplay.content.service.service.MessageByLocaleDatabaseService;
import com.salesplay.content.service.service.SiteLocaleDatabaseService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
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
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(SiteLocaleController.class)
@EnableSpringDataWebSupport
@EnableWebMvc
public class SiteLocaleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private SiteLocale localeMock;

    @MockBean
    private MessageByLocaleDatabaseService messageByLocaleDatabaseService;

    @MockBean
    private SiteLocaleDatabaseService service;

    @Autowired
    private ObjectMapper mapper;

    @Before
    public void setUp() throws Exception {
        localeMock = SiteLocale.of("English", "en", false, false);
    }

    @Test
    public void canRetrieveListOfLocales() throws Exception {
        List<SiteLocale> locales = new ArrayList<>();
        locales.add(localeMock);
        locales.add(localeMock);
        locales.add(localeMock);

        Page<SiteLocale> pagedResponse = new PageImpl(locales);

        when(service.findAll(PageRequest.of(0, 20))).thenReturn(pagedResponse);

        mockMvc.perform(get("/locales"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    public void canRetrieveLocaleByIdWhenExists() throws Exception {
        when(service.findById(1L)).thenReturn(localeMock);

        String actual = mapper.writeValueAsString(localeMock);

        ResultActions resultActions = mockMvc.perform(get("/locales/{id}", 1L))
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
    public void shouldThrowResourceNotFoundExceptionWhenFindByIdLocaleDoesNotExist() throws Exception {
        when(service.findById(1L)).thenThrow(new ResourceNotFoundException("1"));

        mockMvc.perform(get("/locales/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldThrowDuplicateResourceExceptionWhenLocaleAlreadyExists() throws DuplicateResourceException {
        when(service.create(localeMock)).thenThrow(new DuplicateResourceException(localeMock.getCode()));
    }

    @Test
    public void canBulkDeleteALocaleWithValidId() throws Exception {
        List<SiteLocale> locales = new LinkedList<>();
        locales.add(localeMock);

        mockMvc.perform(post("/locales/delete").contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(locales))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void canPostLocaleWithValidInput() throws Exception {
        when(service.create(localeMock)).thenReturn(localeMock);

        String actual = mapper.writeValueAsString(localeMock);

        ResultActions resultActions = mockMvc.perform(post("/locales")
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
}
