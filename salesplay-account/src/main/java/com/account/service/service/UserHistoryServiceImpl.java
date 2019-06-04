package com.account.service.service;

import com.account.service.entity.UserHistory;
import com.account.service.exception.UserNotFoundException;
import com.account.service.repository.UserHistoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserHistoryServiceImpl implements UserHistoryService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private UserHistoryRepository userHistoryRepository;

    @Autowired
    public UserHistoryServiceImpl(UserHistoryRepository userHistoryRepository) {
        this.userHistoryRepository = userHistoryRepository;
    }

    public List<UserHistory> findRevisionsById(Long id) throws UserNotFoundException {
        log.debug("findById called");
        List<UserHistory> histories = userHistoryRepository.listUserRevisionsByUserId(id);

        if (histories.isEmpty()) {
            throw UserNotFoundException.createWith(id.toString());
        }

        return histories;
    }

    public List<UserHistory> findRevisionsByEmail(String email) throws UserNotFoundException {
        log.debug("findByEmail called");
        List<UserHistory> histories = userHistoryRepository.listUserRevisionsByUserEmail(email);

        if (histories.isEmpty()) {
            throw UserNotFoundException.createWith(email);
        }

        return histories;
    }
}
