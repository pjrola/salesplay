package com.account.service.controller;

import com.account.service.exception.UserNotFoundException;
import com.account.service.service.UserHistoryServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class UserHistoryController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final UserHistoryServiceImpl userHistoryService;

    @Autowired
    public UserHistoryController(UserHistoryServiceImpl userHistoryService) {
        this.userHistoryService = userHistoryService;
    }

    @GetMapping("/users/history/{id}")
    @ResponseBody
    public ResponseEntity<List> getHistory(@PathVariable(value = "id") Long userId) throws UserNotFoundException {
        log.info("getHistory called with user {}", userId);
        return new ResponseEntity<>(userHistoryService.findRevisionsById(userId), HttpStatus.OK);
    }

    @GetMapping("/users/history/email/{id}")
    public ResponseEntity<List> getHistory(@PathVariable(value = "id") String email) throws UserNotFoundException {
        log.info("getHistory called with user {}", email);
        return new ResponseEntity<>(userHistoryService.findRevisionsByEmail(email), HttpStatus.OK);
    }
}
