package com.auth.service.controller;

import com.auth.service.exception.ExceptionResponse;
import com.auth.service.exception.ResourceNotFoundException;
import com.auth.service.service.MessageByLocaleService;
import com.auth.service.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlingController {

    private MessageByLocaleService messageByLocaleService;

    @Autowired
    public ExceptionHandlingController(MessageByLocaleService messageByLocaleService) {
        this.messageByLocaleService = messageByLocaleService;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> resourceNotFound(ResourceNotFoundException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode(messageByLocaleService.getMessage("not.found.msg"));
        response.setErrorMessage(ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> invalidInput(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode(messageByLocaleService.getMessage("validation.error.msg"));
        response.setErrorMessage(messageByLocaleService.getMessage("invalid.input.msg"));
        response.setErrors(ValidationUtil.fromBindingErrors(result));
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
