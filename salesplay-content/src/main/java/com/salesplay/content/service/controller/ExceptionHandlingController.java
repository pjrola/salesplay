package com.salesplay.content.service.controller;

import com.salesplay.content.service.exception.DuplicateResourceException;
import com.salesplay.content.service.exception.ExceptionResponse;
import com.salesplay.content.service.exception.ResourceNotFoundException;
import com.salesplay.content.service.service.MessageByLocaleDatabaseService;
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

    private MessageByLocaleDatabaseService service;

    @Autowired
    public ExceptionHandlingController(MessageByLocaleDatabaseService service) {
        this.service = service;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> resourceNotFound(ResourceNotFoundException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode(service.getMessage("error.not.found"));
        response.setErrorMessage(service.getMessageWithParam("error.not.found.msg", new Object[] {
                ex.getProperty(),
        }));
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ExceptionResponse> duplicateResource(DuplicateResourceException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode(service.getMessage("error.duplicate"));
        response.setErrorMessage(service.getMessageWithParam("error.duplicate.msg", new Object[] {
                ex.getResource(),
        }));
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> invalidInput(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode(service.getMessage("validation.error.msg"));
        response.setErrorMessage(service.getMessage("invalid.input.msg"));
        response.setErrors(ValidationUtil.fromBindingErrors(result));
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
