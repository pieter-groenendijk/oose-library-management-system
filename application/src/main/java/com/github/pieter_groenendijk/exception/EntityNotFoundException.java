package com.github.pieter_groenendijk.exception;

public class EntityNotFoundException extends RuntimeException {
    
    public EntityNotFoundException(String message) {
        super(message);
    }
    
    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public EntityNotFoundException(String entityName, Object identifier) {
        super(String.format("%s with identifier '%s' not found", entityName, identifier));
    }
}