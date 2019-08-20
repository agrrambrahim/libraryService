package com.luxoft.libraryservice.rest.exceptions;

import java.io.IOException;

import org.springframework.web.client.HttpStatusCodeException;

public class NotAvailableResultsException extends IOException {
    public NotAvailableResultsException(String s, HttpStatusCodeException e) {
        super(s, e);
    }
    public NotAvailableResultsException(String s) {
        super(s);
    }
}
