package com.luxoft.libraryservice.upstream.client.itunes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luxoft.libraryservice.upstream.client.UpStreamClient;
import com.luxoft.libraryservice.searchservice.dao.SearchResult;
import com.luxoft.libraryservice.upstream.client.itunes.endpoint.ItunesApiClient;
import com.luxoft.libraryservice.upstream.client.itunes.json.AlbumsWrapper;

@Service
public class ItunesAlbumsService implements UpStreamClient {
    static final String UPSTREAM_NAME = "iTunes Search API";
    static final String SERVED_MEDIA_CATEGORY_NAME = "Album";

    @Autowired
    private ItunesApiClient itunesApiClient;

    @Override
    public SearchResult fetchForMediasBy(String terms, int resultLimits){
        AlbumsWrapper jsonResponse = itunesApiClient.retrieve(terms, resultLimits);
        return ItunesAlbumsMapper.mapResults(jsonResponse);
    }

    @Override
    public String getServedMediaCategory() {
        return SERVED_MEDIA_CATEGORY_NAME;
    }
}
