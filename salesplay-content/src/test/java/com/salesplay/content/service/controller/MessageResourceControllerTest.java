package com.salesplay.content.service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.salesplay.content.service.domain.MessageResource;
import com.salesplay.content.service.exception.DuplicateResourceException;
import com.salesplay.content.service.exception.ResourceNotFoundException;
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
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MessageResourceController.class)
@EnableSpringDataWebSupport
@EnableWebMvc
public class MessageResourceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MessageByLocaleDatabaseService msgService;

    @MockBean
    private SiteLocaleDatabaseService siteLocaleDatabaseService;

    @Autowired
    private ObjectMapper mapper;

    private MessageResource messageResource;

    private static final String RESOURCE_PATH = "/messageResources";

    @Before
    public void setUp() throws Exception {
        messageResource = MessageResource.of("en", "key", "content", true, false);
    }

    @Test
    public void canRetrieveListOfMessages() throws Exception {
        List<MessageResource> messageResources = new ArrayList<>();
        messageResources.add(messageResource);
        messageResources.add(messageResource);
        messageResources.add(messageResource);

        Page<MessageResource> pagedResponse = new PageImpl(messageResources);

        when(msgService.findAll(PageRequest.of(0, 20))).thenReturn(pagedResponse);

        mockMvc.perform(get(RESOURCE_PATH))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    public void canRetrieveByIdWhenExists() throws Exception {
        when(msgService.findById(1L)).thenReturn(messageResource);

        String actual = mapper.writeValueAsString(messageResource);

        ResultActions resultActions = mockMvc.perform(get(RESOURCE_PATH + "/{id}", 1L))
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
        when(msgService.findById(1L)).thenThrow(new ResourceNotFoundException("1"));

        mockMvc.perform(get(RESOURCE_PATH + "/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldThrowDuplicateResourceExceptionWhenLocaleAlreadyExists() throws DuplicateResourceException {
        when(msgService.create(messageResource)).thenThrow(new DuplicateResourceException(messageResource.getKey()));
    }

    @Test
    public void canBulkDeleteALocaleWithValidId() throws Exception {
        List<MessageResource> messageResources = new LinkedList<>();
        messageResources.add(messageResource);

        mockMvc.perform(post(RESOURCE_PATH + "/delete").contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(messageResources))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void canPostLocaleWithValidInput() throws Exception {
        when(msgService.create(messageResource)).thenReturn(messageResource);

        String actual = mapper.writeValueAsString(messageResource);

        ResultActions resultActions = mockMvc.perform(post(RESOURCE_PATH)
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
