package com.luxoft.libraryservice.web.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Response implements Serializable {
    @JsonProperty("Search results")
    List<ResponseElementDTO> results;
    @JsonProperty("Upstream response time")
    Map<String,String> upstreamsResponseTime;
    @JsonProperty("Error Messages")
    List<String> errorMessages;

    public Response(List<ResponseElementDTO> results, Map<String, String> upstreamsResponseTime, List<String> errorMessages) {
        this.results = results;
        this.upstreamsResponseTime = upstreamsResponseTime;
        this.errorMessages = errorMessages;
    }
}
