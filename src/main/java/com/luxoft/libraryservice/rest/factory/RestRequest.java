package com.luxoft.libraryservice.rest.factory;

import java.net.URI;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import io.micrometer.core.annotation.Timed;

public class RestRequest<T> {

    private final RestTemplate restTemplate;
    private final HttpEntity httpEntity;
    private final URI serviceURL;
    private final Class<T> outputClass;

    RestRequest(RestTemplate restTemplate, HttpEntity httpEntity, URI serviceURL, Class<T> outputClass) {
        this.restTemplate = restTemplate;
        this.httpEntity = httpEntity;
        this.serviceURL = serviceURL;
        this.outputClass = outputClass;
    }

    public T invokeGet() {
        ResponseEntity<T> response = restTemplate.exchange(serviceURL, HttpMethod.GET, httpEntity, outputClass);
        return response.getBody();
    }

    public ResponseEntity<T> invokeGetRequest() {
        ResponseEntity<T> response = restTemplate.exchange(serviceURL, HttpMethod.GET, httpEntity, outputClass);
        return response;
    }
}

