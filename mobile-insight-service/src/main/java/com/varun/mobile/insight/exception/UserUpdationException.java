package com.varun.mobile.insight.exception;

public class UserUpdationException extends Exception {

    public UserUpdationException(String message) {
        super(message);
    }

    public UserUpdationException(String message, Throwable err) {
        super(message, err);
    }
}
