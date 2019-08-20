package com.luxoft.libraryservice.studio.itunes.json;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseErrorJSONWrapper {

    private final List<ResponseErrorJSON> queryParameters;
    private final String message;

    @JsonCreator
    public ResponseErrorJSONWrapper(@JsonProperty("queryParameters") List<ResponseErrorJSON> responseErrors, @JsonProperty("errorMessage") String message) {
        this.queryParameters = responseErrors;
        this.message = message;
    }

    public List<ResponseErrorJSON> getResponseError() {
        return queryParameters;
    }

    public String getMessage() {
        return message;
    }
}