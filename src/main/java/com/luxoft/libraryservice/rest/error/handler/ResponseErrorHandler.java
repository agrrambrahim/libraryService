package com.luxoft.libraryservice.rest.error.handler;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.io.IOException;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.HttpClientErrorException;

import com.luxoft.libraryservice.exceptions.NotAvailableResultsException;
import com.luxoft.libraryservice.rest.error.DefaultResponseErrorWrapper;

public abstract class ResponseErrorHandler extends DefaultResponseErrorHandler {

    @Override
    public void handleError(ClientHttpResponse response) {
        try {
            if (NOT_FOUND == response.getStatusCode() || FORBIDDEN == response.getStatusCode()) {
                throw new NotAvailableResultsException(parseError(response).getErrorMessage(), new HttpClientErrorException(response.getStatusCode()));
            }
            super.handleError(response);
        } catch (IOException exception) {
            throw new NotAvailableResultsException(parseError(response).getErrorMessage(), exception);
        }
    }

    public abstract DefaultResponseErrorWrapper parseError(ClientHttpResponse response);
}
