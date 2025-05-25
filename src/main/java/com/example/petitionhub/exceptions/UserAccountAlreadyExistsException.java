package com.example.petitionhub.exceptions;

public class UserAccountAlreadyExistsException extends RuntimeException {
    public UserAccountAlreadyExistsException(String message) {
        super(message);
    }
}
