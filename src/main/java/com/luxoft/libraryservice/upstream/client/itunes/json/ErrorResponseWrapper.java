package com.luxoft.libraryservice.upstream.client.itunes.json;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.luxoft.libraryservice.rest.error.DefaultResponseErrorWrapper;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorResponseWrapper extends DefaultResponseErrorWrapper {

    private final List<ErrorResponse> queryParameters;
    private final String errorMessage;

    @JsonCreator
    public ErrorResponseWrapper(@JsonProperty("queryParameters") List<ErrorResponse> responseErrors, @JsonProperty("errorMessage") String errorMessage) {
        this.queryParameters = responseErrors;
        this.errorMessage = errorMessage;
    }

    public List<ErrorResponse> getResponseError() {
        return queryParameters;
    }
    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
}