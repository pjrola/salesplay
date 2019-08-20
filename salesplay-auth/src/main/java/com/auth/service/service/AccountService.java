package com.auth.service.service;

import com.auth.service.entity.Account;
import com.auth.service.entity.PasswordResetToken;
import com.auth.service.entity.VerificationToken;
import com.auth.service.exception.AccountNotFoundException;
import com.auth.service.exception.DuplicateResourceException;
import com.auth.service.exception.TokenNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AccountService extends UserDetailsService {

    Page<Account> findAll(Pageable pageable);

    Account save(Account account) throws DuplicateResourceException;

    Account update(Account account) throws AccountNotFoundException;

    void delete(Long id) throws AccountNotFoundException;

    void disableAccount(String email) throws AccountNotFoundException;

    void enableAccount(String email) throws AccountNotFoundException;

    Account findByEmail(String email) throws AccountNotFoundException;

    Account findById(Long id) throws AccountNotFoundException;

    Account assignRoles(Account account) throws AccountNotFoundException;

    Account removeRoles(Account account) throws AccountNotFoundException;

    void createVerificationToken(Account account, String token);

    VerificationToken getVerificationToken(String verificationToken) throws TokenNotFoundException;

    VerificationToken generateNewVerificationToken(final String existingVerificationToken) throws TokenNotFoundException;

    void createPasswordResetToken(Account account, String token) throws AccountNotFoundException;

    public PasswordResetToken getPasswordResetToken(String token) throws TokenNotFoundException;

    void deletePasswordResetToken(Long id) throws TokenNotFoundException;

    public Account updatePassword(Account account, String password) throws AccountNotFoundException;

    public Boolean isEmailAlreadyInUse(String email);

}
