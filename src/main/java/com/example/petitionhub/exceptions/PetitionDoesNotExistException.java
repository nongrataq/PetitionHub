package com.example.petitionhub.exceptions;

public class PetitionDoesNotExistException extends RuntimeException {
    public PetitionDoesNotExistException(String message) {
        super(message);
    }
}
