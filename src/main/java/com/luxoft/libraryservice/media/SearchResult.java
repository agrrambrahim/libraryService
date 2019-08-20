package com.luxoft.libraryservice.media;

import com.luxoft.libraryservice.media.model.Media;
import java.util.List;

public class SearchResult {

    private List<Media> searchResults;
    private String usedUpStream;
    private float responseTime;

    public SearchResult(List<Media> searchResults, String usedUpStream, float responseTime) {
        this.searchResults = searchResults;
        this.usedUpStream = usedUpStream;
        this.responseTime = responseTime;
    }

    public List<Media> getSearchResults() {
        return searchResults;
    }

    public void setSearchResults(List<Media> searchResults) {
        this.searchResults = searchResults;
    }

    public String getUsedUpStream() {
        return usedUpStream;
    }

    public void setUsedUpStream(String usedUpStream) {
        this.usedUpStream = usedUpStream;
    }

    public float getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(float responseTime) {
        this.responseTime = responseTime;
    }
}
