package com.varun.mobile.insight.exception;

public class CycleUsageException extends Exception {

    public CycleUsageException(String message) {
        super(message);
    }

    public CycleUsageException(String message, Throwable err) {
        super(message, err);
    }
}