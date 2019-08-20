package com.luxoft.libraryservice.rest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseErrorJSON {

    private final String type;
    private final String message;
    private final String status;
    private final String request;
    private final String stack;

    @JsonCreator
    public ResponseErrorJSON(@JsonProperty("type") String type,
                             @JsonProperty("message") String message,
                             @JsonProperty("status") String status,
                             @JsonProperty("request") String request,
                             @JsonProperty("stack") String stack) {
        this.type = type;
        this.message = message;
        this.status = status;
        this.request = request;
        this.stack = stack;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public String getRequest() {
        return request;
    }

    public String getStack() {
        return stack;
    }
}
