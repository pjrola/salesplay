package com.auth.service.service;

import com.auth.service.entity.*;
import com.auth.service.exception.AccountNotFoundException;
import com.auth.service.exception.DuplicateResourceException;
import com.auth.service.exception.TokenNotFoundException;
import com.auth.service.repository.AccountRepository;
import com.auth.service.repository.PasswordTokenRepository;
import com.auth.service.repository.VerificationTokenRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class AccountServiceImpl implements AccountService {

    private BCryptPasswordEncoder encoder;
    private AccountRepository accountRepository;
    private VerificationTokenRepository verificationTokenRepository;
    private PasswordTokenRepository passwordTokenRepository;
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private String tokenNotFound;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, BCryptPasswordEncoder encoder,
                              VerificationTokenRepository verificationTokenRepository,
                              PasswordTokenRepository passwordTokenRepository) {
        this.encoder = encoder;
        this.accountRepository = accountRepository;
        this.verificationTokenRepository = verificationTokenRepository;
        this.passwordTokenRepository = passwordTokenRepository;
    }

    public Page<Account> findAll(Pageable pageable) {
        return accountRepository.findAll(pageable);
    }

    public Account save(Account account) throws DuplicateResourceException {
        log.debug("saveAccount called");
        Optional<Account> duplicateAccount = accountRepository.findByEmail(account.getEmail());

        if (duplicateAccount.isPresent()) {
            throw DuplicateResourceException.createWith(account.getEmail());
        }

        account.setPassword(encoder.encode(account.getPassword()));
        return accountRepository.save(account);
    }

    public void createVerificationToken(Account account, String token) {
        VerificationToken myToken = new VerificationToken(token, account);
        verificationTokenRepository.save(myToken);
    }

    public VerificationToken getVerificationToken(String verificationToken) throws TokenNotFoundException  {
        return verificationTokenRepository.findByToken(verificationToken).orElseThrow(()
                -> new TokenNotFoundException("Token not found"));
    }

    public VerificationToken generateNewVerificationToken(final String existingVerificationToken) throws TokenNotFoundException {
        VerificationToken vToken = verificationTokenRepository.findByToken(existingVerificationToken).orElseThrow(()
                -> new TokenNotFoundException("Token not found"));

        vToken.updateToken(UUID.randomUUID().toString());
        vToken = verificationTokenRepository.save(vToken);
        return vToken;
    }

    public void createPasswordResetToken(Account account, String token) throws AccountNotFoundException {
        PasswordResetToken myToken = new PasswordResetToken(token, account);
        passwordTokenRepository.save(myToken);
    }

    public PasswordResetToken getPasswordResetToken(String token) throws TokenNotFoundException {
        return passwordTokenRepository.findByToken(token).orElseThrow(()
                -> new TokenNotFoundException("Token not found"));
    }

    public Account updatePassword(Account account, String password) throws AccountNotFoundException {
        accountRepository.findById(account.getId()).orElseThrow(() -> AccountNotFoundException.createWith(account.getId().toString()));
        account.setPassword(encoder.encode(password));

        return accountRepository.save(account);
    }

    public void deletePasswordResetToken(Long id) throws TokenNotFoundException {
        passwordTokenRepository.deleteById(id);
    }

    public Account update(Account account) throws AccountNotFoundException {
        log.debug("updateAccount called");
        accountRepository.findById(account.getId()).orElseThrow(() -> AccountNotFoundException.createWith(account.getId().toString()));
        return accountRepository.save(account);
    }

    public void delete(Long id) throws AccountNotFoundException {
        log.debug("deleteAccount called");
        Account account = accountRepository.findById(id).orElseThrow(() -> AccountNotFoundException.createWith(id.toString()));
        accountRepository.deleteById(account.getId());
    }

    public void disableAccount(String email) throws AccountNotFoundException {
        log.debug("disableAccount called");
        Account account = accountRepository.findByEmail(email).orElseThrow(() -> AccountNotFoundException.createWith(email));
        account.setEnabled(false);
        accountRepository.save(account);
    }

    public void enableAccount(String email) throws AccountNotFoundException {
        log.debug("enableAccount called");
        Account account = accountRepository.findByEmail(email).orElseThrow(() -> AccountNotFoundException.createWith(email));
        account.setEnabled(true);
        accountRepository.save(account);
    }

    public Account findByEmail(String email) throws AccountNotFoundException {
        return accountRepository.findByEmail(email).orElseThrow(() -> AccountNotFoundException.createWith(email));
    }

    public Account findById(Long id) throws AccountNotFoundException {
        log.debug("findById called");
        return accountRepository.findById(id).orElseThrow(() -> AccountNotFoundException.createWith(id.toString()));
    }

    public Account assignRoles(Account account) throws AccountNotFoundException {
        Account existingAccount = accountRepository.findByEmail(account.getEmail()).orElseThrow(()
                -> AccountNotFoundException.createWith(account.getEmail()));

        existingAccount.addRoles(account.getRoles());

        return accountRepository.save(existingAccount);
    }

    public Account removeRoles(Account account) throws AccountNotFoundException {
        Account existingAccount = accountRepository.findByEmail(account.getEmail()).orElseThrow(() ->
                AccountNotFoundException.createWith(account.getEmail()));

        existingAccount.removeRoles(account.getRoles());

        return accountRepository.save(existingAccount);
    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));

        return new org.springframework.security.core.userdetails.User(
                account.getEmail(), account.getPassword(), account.isEnabled(), true, true,
                true, getAuthorities(account.getRoles()));
    }

    private final Collection<? extends GrantedAuthority> getAuthorities(final Collection<Role> roles) {
        return getGrantedAuthorities(getPrivileges(roles));
    }

    private final List<String> getPrivileges(final Collection<Role> roles) {
        final List<String> privileges = new ArrayList<>();
        final List<Privilege> collection = new ArrayList<>();
        for (final Role role : roles) {
            collection.addAll(role.getPrivileges());
        }
        for (final Privilege item : collection) {
            privileges.add(item.getName());
        }

        return privileges;
    }

    private final List<GrantedAuthority> getGrantedAuthorities(final List<String> privileges) {
        final List<GrantedAuthority> authorities = new ArrayList<>();
        for (final String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }
}