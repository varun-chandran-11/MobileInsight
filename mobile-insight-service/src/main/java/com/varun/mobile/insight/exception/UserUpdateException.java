package com.varun.mobile.insight.exception;

public class UserUpdateException extends Exception {

    public UserUpdateException(String message) {
        super(message);
    }

    public UserUpdateException(String message, Throwable err) {
        super(message, err);
    }
}
