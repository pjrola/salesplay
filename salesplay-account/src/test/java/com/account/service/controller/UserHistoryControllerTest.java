package com.account.service.controller;

import com.account.service.entity.User;
import com.account.service.entity.UserHistory;
import com.account.service.exception.UserNotFoundException;
import com.account.service.service.UserHistoryServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.hibernate.envers.RevisionType.ADD;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserHistoryController.class)
@EnableSpringDataWebSupport
public class UserHistoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserHistoryServiceImpl userHistoryService;

    private UserHistory userHistoryMock;

    private User userMock;

    @Before
    public void setUpUser() throws Exception {
        userMock = new User();
        userMock.setId(1L);
        userMock.setEmail("email");
        userMock.setFirstName("firstname");
        userMock.setLastName("lastname");
        userMock.setCompany("company");
        userMock.setPassword("password");
    }

    @Test
    public void canRetrieveAddUserRevisionsByValidUserId() throws Exception {
        userHistoryMock = new UserHistory(userMock, 1, ADD);
        when(userHistoryService.findRevisionsById(1L)).thenReturn(Collections.singletonList((userHistoryMock)));

        mockMvc.perform(get("/users/history/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));

        verify(userHistoryService, times(1)).findRevisionsById(1L);
        verifyNoMoreInteractions(userHistoryService);
    }

    @Test
    public void canRetrieveAddUserRevisionsByValidEmail() throws Exception {
        userHistoryMock = new UserHistory(userMock, 1, ADD);
        when(userHistoryService.findRevisionsByEmail("test@test.com")).thenReturn(Collections.singletonList((userHistoryMock)));

        mockMvc.perform(get("/users/history/email/{email}", "test@test.com"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));

        verify(userHistoryService, times(1)).findRevisionsByEmail("test@test.com");
        verifyNoMoreInteractions(userHistoryService);
    }

    @Test
    public void shouldThrowUserNotFoundExceptionWhenUserEmailNotExists() throws Exception {
        userHistoryMock = new UserHistory(userMock, 1, ADD);
        when(userHistoryService.findRevisionsByEmail("test@test.com")).thenThrow(UserNotFoundException.createWith("test@test.com"));

        mockMvc.perform(get("/users/history/email/{email}", "test@test.com"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));

        verify(userHistoryService, times(1)).findRevisionsByEmail("test@test.com");
        verifyNoMoreInteractions(userHistoryService);
    }

    @Test
    public void shouldThrowUserNotFoundExceptionWhenUserIdNotExists() throws Exception {
        userHistoryMock = new UserHistory(userMock, 1, ADD);
        when(userHistoryService.findRevisionsById(1L)).thenThrow(UserNotFoundException.createWith("1L"));

        mockMvc.perform(get("/users/history/{id}", 1L))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));

        verify(userHistoryService, times(1)).findRevisionsById(1L);
        verifyNoMoreInteractions(userHistoryService);
    }

}
