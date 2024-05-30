package com.varun.mobile.insight.exception;

public class UserCreationException extends Exception {

    public UserCreationException(String message) {
        super(message);
    }

    public UserCreationException(String message, Throwable err) {
        super(message, err);
    }
}