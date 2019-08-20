package com.luxoft.libraryservice.library.googlebooks.endpoint;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import com.luxoft.libraryservice.rest.CustomURLBuilder;

public class GoogleBooksEndpointsFactory {

    private static final String SEARCH_QUERY_PARAMETER_NAME = "q";
    private static final String API_KEY_PARAMETER_NAME = "key";
    private static final String VOLUMES_RESOURCE = "/volumes";

    private static final String API_KEY = "AIzaSyCP9nBnhyVkvje8OT90_g5Su0DUhFRpG14";
    private static final String MAX_RESULT_PARAMETER_NAME = "maxResults";

    private static final String baseURL = "https://www.googleapis.com/books/v1";
    public static final String LOCALISATION_PARAMETER_NAME = "source";
    public static final String LOCALISATION_PARAMETER_VALUE = "webstore_bookcard";

    URI buildEndpointURLFor(String query, int maxResult) {
        Map<String, String> params = new HashMap<>();
        params.put(SEARCH_QUERY_PARAMETER_NAME, query);
        params.put(MAX_RESULT_PARAMETER_NAME, String.valueOf(maxResult));
        params.put(LOCALISATION_PARAMETER_NAME, LOCALISATION_PARAMETER_VALUE);
        params.put(API_KEY_PARAMETER_NAME, API_KEY);

        return CustomURLBuilder.get(baseURL, VOLUMES_RESOURCE, params);
    }
}
