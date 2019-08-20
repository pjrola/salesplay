package com.auth.service.util.validate;

import com.auth.service.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    private AccountService accountService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && !accountService.isEmailAlreadyInUse(value);
    }
}
