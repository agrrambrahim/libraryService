package com.luxoft.libraryservice.exceptions;

public class NotAvailableResultsException extends Error {
    public NotAvailableResultsException(String s, Exception e) {
        super(s, e);
    }
}
