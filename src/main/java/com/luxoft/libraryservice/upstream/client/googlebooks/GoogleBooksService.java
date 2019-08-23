package com.luxoft.libraryservice.upstream.client.googlebooks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luxoft.libraryservice.upstream.client.googlebooks.endpoint.GoogleBooksApiClient;
import com.luxoft.libraryservice.upstream.client.googlebooks.json.VolumeWrapper;
import com.luxoft.libraryservice.upstream.client.UpStreamClient;
import com.luxoft.libraryservice.searchservice.dao.SearchResult;

@Service
public class GoogleBooksService implements UpStreamClient {
    static final String UPSTREAM_NAME = "Google Books API";
    static final String SERVED_MEDIA_CATEGORY_NAME = "Book";

    @Autowired
    private GoogleBooksApiClient googleBooksApiClient;

    @Override
    public SearchResult fetchForMediasBy(String terms, int resultLimits){
        VolumeWrapper response = googleBooksApiClient.retrieve(terms, resultLimits);
        return GoogleBooksMapper.mapResults(response);
    }

    @Override
    public String getServedMediaCategory() {
        return SERVED_MEDIA_CATEGORY_NAME;
    }
}
