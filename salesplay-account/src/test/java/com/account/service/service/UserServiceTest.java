package com.account.service.service;

import com.account.service.entity.User;
import com.account.service.repository.UserRepository;
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

@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private User user;

    private UserServiceImpl userService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        userService = new UserServiceImpl();
        userService.setUserRepository(userRepository);
        user = new User();
        user.setId(1L);
        user.setEmail("email");
        user.setFirstName("firstname");
        user.setLastName("lastname");
        user.setCompany("company");
        user.setPassword("password");
    }

    @Test
    public void canRetrievePageListOfUsers() throws Exception {
        List<User> userList = new ArrayList<>();
        userList.add(user);
        userList.add(user);
        userList.add(user);
        Page<User> pagedResponse = new PageImpl(userList);
        when(userRepository.findAll(PageRequest.of(0, 10))).thenReturn(pagedResponse);

        Page<User> result = userService.findAll(PageRequest.of(0, 10));
        assertTrue(result.hasContent());
        assertEquals(3, result.getTotalElements());
    }

    @Test
    public void canRetrieveUserById() throws Exception {
        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(user));
        Optional<User> result = Optional.ofNullable(userService.findById(1L));
        assertTrue(result.isPresent());
        assertEquals(result.get(), user);
    }

    @Test
    public void canRetrieveUserByEmail() throws Exception {
        when(userRepository.findByEmail("email")).thenReturn(Optional.of(user));
        Optional<User> result = Optional.ofNullable(userService.findByEmail("email"));
        assertTrue(result.isPresent());
        assertEquals(user, result.get());
    }

    @Test
    public void canRetrieveUserByLastName() throws Exception {
        when(userRepository.findByLastName("lastname")).thenReturn(Optional.of(user));
        Optional<User> result = Optional.ofNullable(userService.findByLastName("lastname"));
        assertTrue(result.isPresent());
        assertEquals(result.get(), user);
    }

    @Test
    public void canSaveUser() throws Exception {
        when(userRepository.save(user)).thenReturn(user);
        User savedUser = userService.save(user);
        assertEquals(savedUser, user);
    }

    @Test
    public void canUpdateUser() throws Exception {
        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(user));
        Optional<User> result = Optional.ofNullable(userService.findById(1L));
        result.get().setCompany("test");
        result.get().setEmail("test");
        result.get().setFirstName("test");
        result.get().setLastName("test");
        result.get().setPassword("test");

        userService.update(result.get());
        verify(userRepository, times(1)).save(result.get());
    }

    @Test
    public void canDeleteUser() throws Exception {
        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(user));
        Optional<User> result = Optional.ofNullable(userService.findById(1L));
        userService.delete(result.get().getId());
        verify(userRepository, times(1)).deleteById(result.get().getId());
    }

}
