package com.luxoft.libraryservice.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import org.apache.http.client.utils.URIBuilder;

public class CustomURLBuilder {
    public static URI get(String baseURL, String resource, Map<String, String> params) {
        URI url = null;
        try {
            url = getURI(baseURL, resource, params);
        } catch (URISyntaxException e) {
        }
        return url;
    }

    public static URI getURI(String baseURL, String resource, Map<String, String> params) throws URISyntaxException {
        String endpoint = baseURL + resource;
        URIBuilder uriBuilder = new URIBuilder(endpoint);
        for (Map.Entry<String, String> paramKey : params.entrySet()) {
            uriBuilder.addParameter(paramKey.getKey(), paramKey.getValue());
        }
        return uriBuilder.build();
    }
}
