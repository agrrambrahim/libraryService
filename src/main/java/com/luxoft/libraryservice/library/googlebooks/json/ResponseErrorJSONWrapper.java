package com.luxoft.libraryservice.library.googlebooks.json;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseErrorJSONWrapper {

    private final List<ResponseErrorJSON> responseErrors;
    private final String code;
    private final String message;

    @JsonCreator
    public ResponseErrorJSONWrapper(@JsonProperty("errors") List<ResponseErrorJSON> responseErrors,
                                    @JsonProperty("code") String code,
                                    @JsonProperty("message") String message) {
        this.responseErrors = responseErrors;
        this.code = code;
        this.message = message;
    }

    public List<ResponseErrorJSON> getResponseError() {
        return responseErrors;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}