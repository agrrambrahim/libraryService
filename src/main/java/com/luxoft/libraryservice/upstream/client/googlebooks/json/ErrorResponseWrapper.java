package com.luxoft.libraryservice.upstream.client.googlebooks.json;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.luxoft.libraryservice.rest.error.DefaultResponseErrorWrapper;

public class ErrorResponseWrapper extends DefaultResponseErrorWrapper {

    private final List<ErrorResponse> responseErrors;
    private final String code;
    private final String message;

    @JsonCreator
    public ErrorResponseWrapper(@JsonProperty("errors") List<ErrorResponse> responseErrors,
                                @JsonProperty("code") String code,
                                @JsonProperty("message") String message) {
        this.responseErrors = responseErrors;
        this.code = code;
        this.message = message;

    }

    public List<ErrorResponse> getResponseError() {
        return responseErrors;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String getErrorMessage() {
        return this.message;
    }
}