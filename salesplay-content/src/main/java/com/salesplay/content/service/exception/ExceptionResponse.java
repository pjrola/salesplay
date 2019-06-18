package com.salesplay.content.service.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {
    private String errorCode;
    private String errorMessage;
    private List<String> errors;
}