package com.auth.service.controller;

import com.auth.service.dto.AccountDTO;
import com.auth.service.dto.AccountRolesDTO;
import com.auth.service.dto.AccountUpdateDTO;
import com.auth.service.dto.PasswordResetDTO;
import com.auth.service.entity.Account;
import com.auth.service.entity.PasswordResetToken;
import com.auth.service.entity.VerificationToken;
import com.auth.service.event.OnRegistrationCompleteEvent;
import com.auth.service.event.OnResendVerificationTokenEmail;
import com.auth.service.exception.AccountNotFoundException;
import com.auth.service.exception.DuplicateResourceException;
import com.auth.service.exception.TokenNotFoundException;
import com.auth.service.service.AccountServiceImpl;
import com.auth.service.util.DTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import java.util.Locale;
import java.util.UUID;

@RestController
public class AccountController {

    ApplicationEventPublisher eventPublisher;
    private AccountServiceImpl accountService;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public AccountController(AccountServiceImpl accountService, ApplicationEventPublisher eventPublisher) {
        this.accountService = accountService;
        this.eventPublisher = eventPublisher;
    }

    @GetMapping("/accounts")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Page<Account> findAll(Pageable pageable) {
        log.info("findAll called with account");
        return accountService.findAll(pageable);
    }

    @PostMapping("/accounts")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Account create(@DTO(AccountDTO.class) Account account, WebRequest request) throws DuplicateResourceException {
        log.info("create called with account {}", account);

        Account registered = accountService.save(account);

        try {
            String appUrl = request.getContextPath();
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent
                    (registered, request.getLocale(), appUrl));
        } catch (Exception e) {
            log.info("publishEvent failed with exception {}", e);
        }

        return registered;
    }

    @GetMapping("/accounts/registrationConfirm/{token}")
    @ResponseStatus(HttpStatus.OK)
    public String confirmRegistration(@PathVariable(value = "token") String token, WebRequest request) throws TokenNotFoundException, AccountNotFoundException {
        Locale locale = request.getLocale();
        VerificationToken verificationToken = accountService.getVerificationToken(token);
        Account account = verificationToken.getAccount();

        if (verificationToken.isExpired()) {
            return "token is expired";
        }

        account.setEnabled(true);
        accountService.update(account);

        return "Verified";
    }

    @GetMapping("/accounts/resendRegistrationToken/{token}")
    @ResponseStatus(HttpStatus.OK)
    public String resendRegistrationToken(@PathVariable(value = "token") String existingToken, WebRequest request) throws TokenNotFoundException, AccountNotFoundException {
        VerificationToken newToken = accountService.generateNewVerificationToken(existingToken);
        Account account = newToken.getAccount();

        eventPublisher.publishEvent(new OnResendVerificationTokenEmail(account, request.getLocale(), "test"));

        return "resent token";
    }

    @PostMapping("/accounts/resetPassword/{email}")
    @ResponseStatus(HttpStatus.OK)
    public String resetPassword(@PathVariable(value = "email") String email) throws AccountNotFoundException {
        Account account = accountService.findByEmail(email);
        String token = UUID.randomUUID().toString();

        accountService.createPasswordResetToken(account, token);

        return "password reset sent";
    }

    @PostMapping("/accounts/updatePassword/{token}")
    public String updatePassword(@PathVariable(value = "token") String token, @DTO(PasswordResetDTO.class) Account account) throws TokenNotFoundException, AccountNotFoundException {
        PasswordResetToken pToken = accountService.getPasswordResetToken(token);
        Account existingAccount = pToken.getAccount();

        accountService.updatePassword(existingAccount, account.getPassword());
        accountService.deletePasswordResetToken(pToken.getId());

        return "password updated";
    }

    @GetMapping("/accounts/{id}")
    public ResponseEntity<Account> findById(@PathVariable(value = "id") Long userId) throws AccountNotFoundException {
        log.info("findById called with userId {}", userId);
        return new ResponseEntity<Account>(accountService.findById(userId), HttpStatus.OK);
    }

    @GetMapping("/accounts/email/{email}")
    public ResponseEntity<Account> findByEmail(@PathVariable(value = "email") String email) throws AccountNotFoundException {
        log.info("findById called with userId {}", email);
        return new ResponseEntity<Account>(accountService.findByEmail(email), HttpStatus.OK);
    }

    @DeleteMapping("/accounts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable(value = "id") Long accountId) throws AccountNotFoundException {
        log.info("delete called with account {}", accountId);
        accountService.delete(accountId);
    }

    @PutMapping("/accounts")
    @ResponseStatus(HttpStatus.OK)
    public Account update(@DTO(AccountUpdateDTO.class) Account account) throws AccountNotFoundException {
        log.info("update called with account {}", account);
        return accountService.update(account);
    }

    @PostMapping("/accounts/disable/{email}")
    @ResponseStatus(HttpStatus.OK)
    public void disable(@PathVariable(value = "email") String email) throws AccountNotFoundException {
        log.info("disable called with account {}", email);
        accountService.disableAccount(email);
    }

    @PostMapping("/accounts/enable/{email}")
    @ResponseStatus(HttpStatus.OK)
    public void enable(@PathVariable(value = "email") String email) throws AccountNotFoundException {
        log.info("enable called with account {}", email);
        accountService.enableAccount(email);
    }

    @PostMapping("/accounts/assignRoles")
    @ResponseStatus(HttpStatus.CREATED)
    public Account assignRoles(@DTO(AccountRolesDTO.class) Account account) throws AccountNotFoundException {
        log.info("assignRoles called with account {}", account);
        return accountService.assignRoles(account);
    }

    @PostMapping("/accounts/removeRoles")
    @ResponseStatus(HttpStatus.OK)
    public Account removeRoles(@DTO(AccountRolesDTO.class) Account account) throws AccountNotFoundException {
        log.info("removeRoles called with account {}", account);
        return accountService.removeRoles(account);
    }

}
