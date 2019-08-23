package com.luxoft.libraryservice.upstream.client.itunes.setup;

import org.springframework.beans.factory.annotation.Value;

public class ItunesConfiguration {

    @Value("${com.deploy.environmentName}")
    private String environmentName;
    @Value("${com.iTunes.api.retryRequest.count}")
    private int retryRequestCount;
    @Value("${com.iTunes.api.connectionRequestTimeout}")
    private int connectionRequestTimeout;
    @Value("${com.iTunes.api.baseurl}")
    private String baseUrl;
    @Value("${com.luxoft.upstream.responselimit}")
    private int apiResponseLimit;

    public String getEnvironmentName() {
        return environmentName;
    }

    public int getConnectionRequestTimeout() {
        return connectionRequestTimeout;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public int getApiResponseLimit() {
        return apiResponseLimit;
    }

    public int getUpstreamRequestRetryCount() {
        return retryRequestCount;
    }
}
