package com.luxoft.libraryservice.rest;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.io.IOException;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luxoft.libraryservice.library.googlebooks.json.ResponseErrorJSONWrapper;
import com.luxoft.libraryservice.rest.exceptions.NotAvailableResultsException;
import com.luxoft.libraryservice.rest.exceptions.TechnicalException;

public class ResponseErrorHandler extends DefaultResponseErrorHandler {

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        if (NOT_FOUND == response.getStatusCode() || FORBIDDEN == response.getStatusCode()) {
            throw new NotAvailableResultsException(parseError(response).getMessage());
        }

        super.handleError(response);
    }

    private ResponseErrorJSONWrapper parseError(ClientHttpResponse response) throws TechnicalException {
        try {
            ResponseErrorJSONWrapper responseErrorJSONWrapper = new ObjectMapper().readValue(response.getBody(), ResponseErrorJSONWrapper.class);
            return responseErrorJSONWrapper;
        } catch (IOException e) {
            throw new TechnicalException("Cannot parse error from HttpStatusCodeException", e);
        }
    }
}
