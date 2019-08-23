package com.luxoft.libraryservice.upstream.client.itunes.endpoint;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import com.luxoft.libraryservice.rest.CustomURLBuilder;

public class ItunesEndpointsFactory {
    private static final String SEARCHING_RESOURCE = "/search";
    private static final String SEARCH_QUERY_PARAMETER_NAME = "term";
    private static final String SEARCH_ALBUM_PARAMETER_NAME = "entity";
    private static final String SEARCH_ALBUM_PARAMETER_VALUE = "album";
    private static final String MAX_RESULT_PARAMETER_NAME = "limit";

    private final String baseURL;

    public ItunesEndpointsFactory(String baseURL) {
        this.baseURL = baseURL;
    }

    URI getAlbumsWithMaxResult(String query, int maxResult) {
        Map<String, String> params = new HashMap<>();
        params.put(SEARCH_QUERY_PARAMETER_NAME, query);
        params.put(SEARCH_ALBUM_PARAMETER_NAME, SEARCH_ALBUM_PARAMETER_VALUE);
        params.put(MAX_RESULT_PARAMETER_NAME, String.valueOf(maxResult));
        return CustomURLBuilder.get(baseURL, SEARCHING_RESOURCE, params);
    }
}
