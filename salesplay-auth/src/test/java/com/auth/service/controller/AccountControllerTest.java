package com.auth.service.controller;

import com.auth.service.dto.AccountDTO;
import com.auth.service.entity.Account;
import com.auth.service.exception.AccountNotFoundException;
import com.auth.service.exception.DuplicateResourceException;
import com.auth.service.service.AccountServiceImpl;
import com.auth.service.service.MessageByLocaleServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(AccountController.class)
@EnableSpringDataWebSupport
@AutoConfigureMockMvc(secure = false)
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountServiceImpl accountService;

    @MockBean
    private MessageByLocaleServiceImpl messageByLocaleService;

    @MockBean
    private EntityManager entityManager;

    private Account accountMock;
    private ModelMapper modelMapper;

    @Before
    public void setupAccount() {
        accountMock = new Account();
        accountMock.setId(1L);
        accountMock.setEmail("email@email.com");
        accountMock.setPassword("password");
        accountMock.setEnabled(true);
    }

    @Test
    public void canRetrieveListOfAccounts() throws Exception {

        List<Account> accountList = new ArrayList<>();
        accountList.add(accountMock);
        accountList.add(accountMock);
        accountList.add(accountMock);

        Page<Account> pagedResponse = new PageImpl(accountList);

        when(accountService.findAll(PageRequest.of(0, 20))).thenReturn(pagedResponse);

        mockMvc.perform(get("/accounts"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.content", hasSize(3)))
                .andExpect(jsonPath("$.content[0].id", is(1)))
                .andExpect(jsonPath("$.content[0].email", is("email@email.com")))
                .andExpect(jsonPath("$.content[0].password", is("password")))
                .andExpect(jsonPath("$.content[1].id", is(1)))
                .andExpect(jsonPath("$.content[1].email", is("email@email.com")))
                .andExpect(jsonPath("$.content[1].password", is("password")))
                .andExpect(jsonPath("$.content[2].id", is(1)))
                .andExpect(jsonPath("$.content[2].email", is("email@email.com")))
                .andExpect(jsonPath("$.content[2].password", is("password")))
        ;

        verify(accountService, times(1)).findAll(PageRequest.of(0, 20));
        verifyNoMoreInteractions(accountService);
    }

    @Test
    public void canRetrieveAccountByIdWhenExists() throws Exception {

        when(accountService.findById(1L)).thenReturn(accountMock);

        mockMvc.perform(get("/accounts/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.email", is("email@email.com")))
                .andExpect(jsonPath("$.enabled", is(true)));

        verify(accountService, times(1)).findById(1L);
        verifyNoMoreInteractions(accountService);
    }

    @Test
    public void canRetrieveAccountByEmailWhenExists() throws Exception {

        when(accountService.findByEmail("email@email.com")).thenReturn(accountMock);

        mockMvc.perform(get("/accounts/email/{email}", "email@email.com"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.email", is("email@email.com")))
                .andExpect(jsonPath("$.enabled", is(true)));

        verify(accountService, times(1)).findByEmail("email@email.com");
        verifyNoMoreInteractions(accountService);
    }

    @Test
    public void shouldThrowAccountNotFoundExceptionWhenUserIdNotExists() throws Exception {

        when(accountService.findById(1L)).thenThrow(AccountNotFoundException.createWith("1"));
        mockMvc.perform(get("/accounts/{id}", 1L))
                .andExpect(status().isNotFound());
        verify(accountService, times(1)).findById(1L);
        verifyNoMoreInteractions(accountService);
    }

    @Test
    public void shouldThrowAccountNotFoundExceptionWhenUserEmailNotExists() throws Exception {

        when(accountService.findByEmail("email@email.com")).thenThrow(AccountNotFoundException.createWith("email@email.com"));
        mockMvc.perform(get("/accounts/email/{email}", "email@email.com"))
                .andExpect(status().isNotFound());
        verify(accountService, times(1)).findByEmail("email@email.com");
        verifyNoMoreInteractions(accountService);
    }

    @Test
    public void canCreateNewAccountWithValidRequest() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        AccountDTO accountDTO = modelMapper.map(accountMock, AccountDTO.class);

        when(accountService.save(accountMock)).thenReturn(accountMock);

        mockMvc.perform(post("/accounts").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(accountMock)))
                .andExpect(status().isCreated());

        verify(accountService, times(1)).save(accountMock);
        verifyNoMoreInteractions(accountService);
    }

    @Test
    public void shouldThrowDuplicateResourceExceptionWhenUserAlreadyExists() throws Exception {
        when(accountService.save(accountMock)).thenThrow(DuplicateResourceException.createWith("test@test.com"));
    }

    @Test
    public void canUpdateAccountWithValidRequest() throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        when(accountService.update(accountMock)).thenReturn(accountMock);

        mockMvc.perform(put("/accounts").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(accountMock)))
                .andExpect(status().isOk());

        verify(accountService, times(1)).update(accountMock);
        verifyNoMoreInteractions(accountService);
    }
}