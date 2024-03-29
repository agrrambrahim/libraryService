package com.luxoft.libraryservice.rest.error;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseErrorJSON {
// to unify the error response format of all upstreams
    private final String type;
    private final String message;
    private final String status;
    private final String request;
    private final String fullStackTrace;

    @JsonCreator
    public ResponseErrorJSON(@JsonProperty("type") String type,
                             @JsonProperty("message") String message,
                             @JsonProperty("status") String status,
                             @JsonProperty("request") String request,
                             @JsonProperty("stack") String fullStackTrace) {
        this.type = type;
        this.message = message;
        this.status = status;
        this.request = request;
        this.fullStackTrace = fullStackTrace;
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

    public String getfullStackTrace() {
        return fullStackTrace;
    }

    public String getErrorMessage() {
        return message;
    }
}
