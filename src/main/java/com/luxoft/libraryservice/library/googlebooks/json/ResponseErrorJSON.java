package com.luxoft.libraryservice.library.googlebooks.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseErrorJSON {

    private final String domain;
    private final String reason;
    private final String message;

    @JsonCreator
    public ResponseErrorJSON(@JsonProperty("domain") String domain,
                             @JsonProperty("reason") String reason,
                             @JsonProperty("message") String message) {
        this.domain = domain;
        this.reason = reason;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getDomain() {
        return domain;
    }

    public String getReason() {
        return reason;
    }
}
