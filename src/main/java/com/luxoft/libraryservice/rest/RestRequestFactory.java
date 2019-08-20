package com.luxoft.libraryservice.rest;

import java.net.URI;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

public class RestRequestFactory {

    private final RestTemplate restTemplate;

    public RestRequestFactory(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public <T> RestRequest<T> create(URI serviceURL, Class<T> outputClass) {
        final HttpEntity<?> httpEntity = new HttpEntity<>(new HttpHeaders());
        return new RestRequest<>(restTemplate, httpEntity, serviceURL, outputClass);
    }
}