package com.luxoft.libraryservice.exceptions;

public class TechnicalException extends Error {

    public TechnicalException(String message, Throwable cause) {
        super(message, cause);
    }
}
