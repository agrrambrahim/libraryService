package com.luxoft.libraryservice.upstream.client.itunes.json;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AlbumsWrapper {
    @JsonProperty("resultCount")
    private int resultCount;
    @JsonProperty("results")
    private List<Album> results = Lists.newArrayList();

    private float responseTime;

    public int getResultCount() {
        return resultCount;
    }

    public void setResultCount(int resultCount) {
        this.resultCount = resultCount;
    }

    public List<Album> getResults() {
        return results;
    }

    public void setResults(List<Album> results) {
        this.results = results;
    }

    public void setResponseTime(String responseTime) {
        this.responseTime = Float.valueOf(responseTime);
    }

    public float getResponseTime() {
        return responseTime;
    }
}
