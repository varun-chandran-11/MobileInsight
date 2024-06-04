package com.varun.mobile.insight.exception;

public class BillingHistoryException extends Exception {

    public BillingHistoryException(String message) {
        super(message);
    }

    public BillingHistoryException(String message, Throwable err) {
        super(message, err);
    }
}