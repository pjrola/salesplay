package com.account.service.controller;

import com.account.service.entity.User;
import com.account.service.exception.DuplicateResourceException;
import com.account.service.exception.UserNotFoundException;
import com.account.service.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
public class UserController {

    private UserServiceImpl userService;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> findById(@PathVariable(value = "id") Long userId) throws UserNotFoundException {
        log.info("findById called with userId {}", userId);
        return new ResponseEntity<>(userService.findById(userId), HttpStatus.OK);
    }

    @GetMapping("/users/email/{email}")
    public ResponseEntity<User> findByEmail(@PathVariable(value = "email") String email) throws UserNotFoundException {
        log.info("findById called with email {}", email);
        return new ResponseEntity<>(userService.findByEmail(email), HttpStatus.OK);
    }

    @GetMapping("/users")
    public Page<User> findAll(Pageable pageable) {
        return userService.findAll(pageable);
    }

    @PostMapping("/users")
    public ResponseEntity<User> create(@Valid @RequestBody User user) throws DuplicateResourceException {
        log.info("create called with user {}", user);
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }

    @PutMapping("/users")
    public ResponseEntity<User> update(@Valid @RequestBody User user) throws UserNotFoundException {
        log.info("update called with user {}", user);
        return new ResponseEntity<>(userService.update(user), HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity delete(@PathVariable(value = "id") Long userId) throws UserNotFoundException {
        log.info("delete called with user {}", userId);
        userService.delete(userId);
        return ResponseEntity.ok().build();
    }
}
