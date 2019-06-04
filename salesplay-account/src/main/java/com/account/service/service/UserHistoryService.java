package com.account.service.service;

import com.account.service.entity.UserHistory;
import com.account.service.exception.UserNotFoundException;
import java.util.List;

public interface UserHistoryService {
    List<UserHistory> findRevisionsById(Long id) throws UserNotFoundException;
    List<UserHistory> findRevisionsByEmail(String email) throws UserNotFoundException;
}
