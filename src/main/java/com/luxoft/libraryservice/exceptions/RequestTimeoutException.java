package com.luxoft.libraryservice.exceptions;

import java.io.IOException;

import org.springframework.web.client.ResourceAccessException;

public class RequestTimeoutException extends ResourceAccessException {
    public RequestTimeoutException(String msg, IOException ex) {
        super(msg, ex);
    }
}
