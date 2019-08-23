package com.luxoft.libraryservice.rest.factory;

import java.util.Collections;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.StandardHttpRequestRetryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import com.luxoft.libraryservice.exceptions.TechnicalException;
import com.luxoft.libraryservice.rest.interceptor.RequestResponseLoggingInterceptor;

public class RestTemplateFactory {

    @Autowired
    RestTemplate restTemplate;

    private final ResponseErrorHandler errorHandler;
    private final Integer connectionRequestTimeout;
    private int requestRetryCount;

    public RestTemplateFactory(int requestRetryCount,
                               int connectionRequestTimeout,
                               ResponseErrorHandler errorHandler) {
        this.requestRetryCount = requestRetryCount;
        this.errorHandler = errorHandler;
        this.connectionRequestTimeout = connectionRequestTimeout;
    }

    private static HttpMessageConverter messageConverter() {
        final MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        return converter;
    }

    public RestTemplateBuilder create() throws TechnicalException {
        ClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory(httpClient());
        ((HttpComponentsClientHttpRequestFactory) httpRequestFactory).setConnectionRequestTimeout(this.connectionRequestTimeout);
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder()
            .additionalMessageConverters(messageConverter())
            .requestFactory(() -> httpRequestFactory)
            .errorHandler(errorHandler)
            .additionalInterceptors(new RequestResponseLoggingInterceptor());
        return restTemplateBuilder;
    }

    private CloseableHttpClient httpClient() throws TechnicalException {
        HttpClientBuilder httpClientBuilder = HttpClients.custom()
            .setRetryHandler(new StandardHttpRequestRetryHandler(requestRetryCount, true));
        if (connectionRequestTimeout != null) {
            //httpClientBuilder.setDefaultRequestConfig(RequestConfig.custom().setConnectionRequestTimeout(connectionRequestTimeout).build());
            httpClientBuilder.setDefaultRequestConfig(RequestConfig.custom().setConnectTimeout(connectionRequestTimeout).build());
            httpClientBuilder.setDefaultRequestConfig(RequestConfig.custom().setSocketTimeout(connectionRequestTimeout).build());
        }
        return httpClientBuilder.build();
    }
}

