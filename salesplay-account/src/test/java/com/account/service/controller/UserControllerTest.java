package com.account.service.controller;

import com.account.service.entity.User;
import com.account.service.exception.DuplicateResourceException;
import com.account.service.exception.UserNotFoundException;
import com.account.service.service.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
@EnableSpringDataWebSupport
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServiceImpl userService;

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
    public void canRetrievePageListOfUsers() throws Exception {

        List<User> userList = new ArrayList<>();
        userList.add(userMock);
        userList.add(userMock);
        userList.add(userMock);
        Page<User> pagedResponse = new PageImpl(userList);

        when(userService.findAll(PageRequest.of(0, 20))).thenReturn(pagedResponse);

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.content", hasSize(3)))

                .andExpect(jsonPath("$.content[0].id", is(1)))
                .andExpect(jsonPath("$.content[0].email", is("email")))
                .andExpect(jsonPath("$.content[0].firstName", is("firstname")))
                .andExpect(jsonPath("$.content[0].lastName", is("lastname")))
                .andExpect(jsonPath("$.content[0].company", is("company")))
                .andExpect(jsonPath("$.content[0].password", is("password")))
                .andExpect(jsonPath("$.content[0].enabled", is(false)))

                .andExpect(jsonPath("$.content[1].id", is(1)))
                .andExpect(jsonPath("$.content[1].email", is("email")))
                .andExpect(jsonPath("$.content[1].firstName", is("firstname")))
                .andExpect(jsonPath("$.content[1].lastName", is("lastname")))
                .andExpect(jsonPath("$.content[1].company", is("company")))
                .andExpect(jsonPath("$.content[1].password", is("password")))
                .andExpect(jsonPath("$.content[0].enabled", is(false)))

                .andExpect(jsonPath("$.content[2].id", is(1)))
                .andExpect(jsonPath("$.content[2].email", is("email")))
                .andExpect(jsonPath("$.content[2].firstName", is("firstname")))
                .andExpect(jsonPath("$.content[2].lastName", is("lastname")))
                .andExpect(jsonPath("$.content[2].company", is("company")))
                .andExpect(jsonPath("$.content[2].password", is("password")))
                .andExpect(jsonPath("$.content[0].enabled", is(false)));

        verify(userService, times(1)).findAll(PageRequest.of(0, 20));
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void canRetrieveUserByIdWhenExists() throws Exception {

        when(userService.findById(1L)).thenReturn(userMock);

        mockMvc.perform(get("/users/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.email", is("email")))
                .andExpect(jsonPath("$.firstName", is("firstname")))
                .andExpect(jsonPath("$.lastName", is("lastname")))
                .andExpect(jsonPath("$.company", is("company")))
                .andExpect(jsonPath("$.enabled", is(false)));

        verify(userService, times(1)).findById(1L);
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void canRetrieveUserByEmailWhenExsists() throws Exception {
        when(userService.findByEmail("email")).thenReturn(userMock);

        mockMvc.perform(get("/users/email/{email}", "email"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.email", is("email")))
                .andExpect(jsonPath("$.firstName", is("firstname")))
                .andExpect(jsonPath("$.lastName", is("lastname")))
                .andExpect(jsonPath("$.company", is("company")))
                .andExpect(jsonPath("$.enabled", is(false)));

        verify(userService, times(1)).findByEmail("email");
        verifyNoMoreInteractions(userService);

    }

    @Test
    public void shouldThrowUserNotFoundExceptionWhenUserIdNotExists() throws Exception {
        when(userService.findById(1L)).thenThrow(UserNotFoundException.createWith("1L"));
        mockMvc.perform(get("/users/{id}", 1L))
                .andExpect(status().isNotFound());
        verify(userService, times(1)).findById(1L);
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void shouldThrowUserNotFoundExceptionWhenUserEmailNotExists() throws Exception {
        when(userService.findByEmail("test@test.com")).thenThrow(UserNotFoundException.createWith("test@test.com"));
        mockMvc.perform(get("/users/email/{email}", "test@test.com"))
                .andExpect(status().isNotFound());
        verify(userService, times(1)).findByEmail("test@test.com");
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void shouldThrowUserNotFoundExceptionWhenUserLastNameNotExists() throws UserNotFoundException {
        when(userService.findByLastName("test")).thenThrow(UserNotFoundException.createWith("test"));

        verify(userService, times(0)).findByLastName("test");
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void canCreateANewUserWithValidRequest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        when(userService.save(userMock)).thenReturn(userMock);

        mockMvc.perform(post("/users").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(userMock)))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldThrowDuplicateResourceExceptionWhenUserAlreadyExists() throws DuplicateResourceException {
        when(userService.save(userMock)).thenThrow(DuplicateResourceException.createWith("test@test.com"));
    }

    @Test
    public void canUpdateAUserWithValidRequest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        when(userService.update(userMock)).thenReturn(userMock);

        mockMvc.perform(put("/users").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(userMock)))
                .andExpect(status().isOk());

        verify(userService, times(1)).update(userMock);
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void canDeleteAUserWithValidId() throws Exception {
        mockMvc.perform(delete("/users/{id}", 1L).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
