package ru.itis.petitionhub.exceptions;

public class UserAccountAlreadyExistsException extends RuntimeException {
    public UserAccountAlreadyExistsException(String message) {
        super(message);
    }
}
