package com.salesplay.content.service.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DuplicateResourceException extends Exception {
    private String resource;
}