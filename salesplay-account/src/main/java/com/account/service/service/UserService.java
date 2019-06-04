package com.account.service.service;

import com.account.service.entity.User;
import com.account.service.exception.DuplicateResourceException;
import com.account.service.exception.UserNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    User save(User user) throws DuplicateResourceException;
    User update(User user) throws UserNotFoundException;
    void delete(Long id) throws UserNotFoundException;
    Page<User> findAll(Pageable pageable);
    User findById(Long id) throws UserNotFoundException;
    User findByEmail(String email) throws UserNotFoundException;
    User findByLastName(String lastName) throws UserNotFoundException;
}
