package com.luxoft.libraryservice.upstream.client.googlebooks.setup;

import org.springframework.beans.factory.annotation.Value;

public class GoogleBooksConfiguration {

    @Value("${com.deploy.environmentName}")
    private String environmentName;
    @Value("${com.googlebooks.api.connectionRequestTimeout}")
    private int connectionRequestTimeout;
    @Value("${com.googlebooks.api.baseurl}")
    private String apiBaseUrl;
    @Value("${com.googlebooks.api.key}")
    private String apiKey;
    @Value("${com.luxoft.upstream.responselimit}")
    private int apiResponseLimit;
    @Value("${com.googlebooks.api.source}")
    private String searchLocalisation;
    @Value("${com.googlebooks.api.retryRequest.count}")
    private int retryUpstreamRequestCount;

    public String getEnvironmentName() {
        return environmentName;
    }

    public int getConnectionRequestTimeout() {
        return connectionRequestTimeout;
    }

    public String getApiBaseUrl() {
        return apiBaseUrl;
    }

    public String getApiKey() {
        return apiKey;
    }

    public int getApiResponseLimit() {
        return apiResponseLimit;
    }

    public String getSearchLocalisation() {
        return searchLocalisation;
    }

    public int getRetryUpStreamRequestCount() {
        return retryUpstreamRequestCount;
    }
}
