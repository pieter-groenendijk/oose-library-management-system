package com.github.pieter_groenendijk.exception;

public class InputValidationException extends RuntimeException {
    
    public InputValidationException(String message) {
        super(message);
    }
    
    public InputValidationException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public InputValidationException(String entityName, Object identifier) {
        super(String.format("Input invalid", entityName, identifier));
    }
}