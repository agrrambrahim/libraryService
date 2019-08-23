package com.luxoft.libraryservice.upstream.client.googlebooks.endpoint;

import java.io.IOException;

import org.springframework.http.client.ClientHttpResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luxoft.libraryservice.exceptions.TechnicalException;
import com.luxoft.libraryservice.upstream.client.googlebooks.json.ErrorResponseWrapper;
import com.luxoft.libraryservice.rest.error.DefaultResponseErrorWrapper;
import com.luxoft.libraryservice.rest.error.handler.ResponseErrorHandler;

public class GoogleBooksResponseErrorHandler extends ResponseErrorHandler {
    @Override
    public DefaultResponseErrorWrapper parseError(ClientHttpResponse response) {
        {
            try {
                ErrorResponseWrapper errorResponseWrapper = new ObjectMapper().readValue(response.getBody(), ErrorResponseWrapper.class);
                return errorResponseWrapper;
            } catch (IOException e) {
                throw new TechnicalException("Cannot parse error from HttpStatusCodeException due to : " + e.getMessage(), e);
            }
        }
    }
}
