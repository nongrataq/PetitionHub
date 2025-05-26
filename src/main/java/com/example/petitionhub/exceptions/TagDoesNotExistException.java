package com.example.petitionhub.exceptions;

public class TagDoesNotExistException extends RuntimeException {
    public TagDoesNotExistException(String message) {
        super(message);
    }
}
