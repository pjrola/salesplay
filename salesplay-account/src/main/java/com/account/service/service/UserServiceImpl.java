package com.account.service.service;

import com.account.service.entity.User;
import com.account.service.exception.DuplicateResourceException;
import com.account.service.exception.UserNotFoundException;
import com.account.service.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) throws DuplicateResourceException {
        log.debug("saveUser called");
        Optional<User> duplicateUser = userRepository.findByEmail(user.getEmail());

        if (duplicateUser.isPresent()) {
            throw DuplicateResourceException.createWith(user.getEmail());
        }

        return userRepository.save(user);
    }

    public User update(User user) throws UserNotFoundException {
        log.debug("updateUser called");
        User userToUpdate = userRepository.findById(user.getId()).orElseThrow(() -> UserNotFoundException.createWith(user.getId().toString()));

        return userRepository.save(user);
    }

    public void delete(Long id ) throws UserNotFoundException {
        log.debug("deleteUser called");
        User user = userRepository.findById(id).orElseThrow(() -> UserNotFoundException.createWith(id.toString()));
        userRepository.deleteById(user.getId());
    }

    public Page<User> findAll(Pageable pageable) {
        log.debug("getAllUsers called");
        return userRepository.findAll(pageable);
    }

    public User findById(Long id) throws UserNotFoundException {
        log.debug("findById called");
        return userRepository.findById(id).orElseThrow(() -> UserNotFoundException.createWith(id.toString()));
    }

    public User findByEmail(String email) throws UserNotFoundException {
        log.debug("findByEmail called");
        return userRepository.findByEmail(email).orElseThrow(() -> UserNotFoundException.createWith(email));
    }

    public User findByLastName(String lastName) throws UserNotFoundException  {
        log.debug("findByLastName called");
        return userRepository.findByLastName(lastName).orElseThrow(() -> UserNotFoundException.createWith(lastName));
    }
}
