package com.example.petitionhub.exceptions;

public class AlreadySignedException extends RuntimeException {
    public AlreadySignedException(String message) {
        super(message);
    }
}
