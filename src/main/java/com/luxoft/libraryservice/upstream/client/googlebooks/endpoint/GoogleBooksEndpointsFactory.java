package com.luxoft.libraryservice.upstream.client.googlebooks.endpoint;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import com.luxoft.libraryservice.rest.CustomURLBuilder;

public class GoogleBooksEndpointsFactory {

    private static final String VOLUMES_RESOURCE = "/volumes";
    private static final String SEARCH_QUERY_PARAMETER_NAME = "q";
    private static final String MAX_RESULT_PARAMETER_NAME = "maxResults";
    private static final String LOCALISATION_PARAMETER_NAME = "source";
    private static final String API_KEY_PARAMETER_NAME = "key";

    private final String baseUrl;
    private final String apiKey;
    private final String localisationSource;

    public GoogleBooksEndpointsFactory(String baseUrl, String apiKey, String localisationSource) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.localisationSource = localisationSource;
    }

    URI buildEndpointURLFor(String query, int maxResult) {
        Map<String, String> params = new HashMap<>();
        params.put(SEARCH_QUERY_PARAMETER_NAME, query);
        params.put(MAX_RESULT_PARAMETER_NAME, String.valueOf(maxResult));
        params.put(API_KEY_PARAMETER_NAME, apiKey);
        params.put(LOCALISATION_PARAMETER_NAME, localisationSource);

        return CustomURLBuilder.get(baseUrl, VOLUMES_RESOURCE, params);
    }
}
