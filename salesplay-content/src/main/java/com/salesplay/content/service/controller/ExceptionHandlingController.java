package com.salesplay.content.service.controller;

import com.salesplay.content.service.exception.DuplicateResourceException;
import com.salesplay.content.service.exception.ExceptionResponse;
import com.salesplay.content.service.exception.ResourceNotFoundException;
import com.salesplay.content.service.service.SiteLocaleDatabaseService;
import com.salesplay.content.service.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlingController {

    private SiteLocaleDatabaseService siteLocaleService;

    @Autowired
    public ExceptionHandlingController(SiteLocaleDatabaseService siteLocaleService) {
        this.siteLocaleService = siteLocaleService;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> resourceNotFound(ResourceNotFoundException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode(siteLocaleService.getMessage("error.not.found"));
        response.setErrorMessage(siteLocaleService.getMessageWithParam("error.not.found.msg", new Object[] {
                ex.getProperty(),
        }));
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ExceptionResponse> duplicateResource(DuplicateResourceException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode(siteLocaleService.getMessage("error.duplicate"));
        response.setErrorMessage(siteLocaleService.getMessageWithParam("error.duplicate.msg", new Object[] {
                ex.getResource(),
        }));
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> invalidInput(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode(siteLocaleService.getMessage("validation.error.msg"));
        response.setErrorMessage(siteLocaleService.getMessage("invalid.input.msg"));
        response.setErrors(ValidationUtil.fromBindingErrors(result));
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
