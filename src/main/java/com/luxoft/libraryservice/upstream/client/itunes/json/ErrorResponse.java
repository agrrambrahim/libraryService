package com.luxoft.libraryservice.upstream.client.itunes.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName("queryParameters")
public class ErrorResponse {

    private final String parameterName;
    private final String description;

    @JsonCreator
    public ErrorResponse(String parameterName,
                         String description) {
        this.parameterName = parameterName;
        this.description = description;
    }

    public String getParameterName() {
        return parameterName;
    }

    public String getDescription() {
        return description;
    }
}
