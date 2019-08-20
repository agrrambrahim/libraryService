package com.luxoft.libraryservice.web;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response implements Serializable {
    @JsonProperty("Search results")
    List<ResponseElementDTO> results;
    @JsonProperty("Upstream response time")
    Map<String,String> upstreamsResponseTime;

    public Response(List<ResponseElementDTO> results,Map<String,String> upstreamsResponseTime) {
        this.results = results;
        this.upstreamsResponseTime = upstreamsResponseTime;
    }

    public List<ResponseElementDTO> getResults() {
        return results;
    }

    public Map<String, String> getUpstreamsResponseTime() {
        return upstreamsResponseTime;
    }
}
