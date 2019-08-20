package com.luxoft.libraryservice.rest.exceptions;

import java.io.IOException;

public class TechnicalException extends IOException {

    public TechnicalException(String message, Throwable cause) {
        super(message, cause);
    }
}
