package com.example.exception;

public class ConflictingAdditionException extends RuntimeException{
    public ConflictingAdditionException(String message) {
        super(message);
    }    
}
