package com.luxoft.libraryservice.searchservice.dao;

import com.luxoft.libraryservice.media.model.Media;
import java.util.List;

//DTO TO TRANSEFER DATA FROM API CLIENTS TO THE MAIN SERVICE
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

    public String getUsedUpStream() {
        return usedUpStream;
    }

    public float getResponseTime() {
        return responseTime;
    }

}
