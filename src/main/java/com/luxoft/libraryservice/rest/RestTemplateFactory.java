package com.luxoft.libraryservice.rest;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.StandardHttpRequestRetryHandler;
import org.apache.http.ssl.SSLContexts;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.luxoft.libraryservice.rest.exceptions.TechnicalException;

public class RestTemplateFactory {

    private static final String PRODUCTION_ENVIRONMENT = "PRODUCTION";

    private final boolean isProdEnvironment;
    private final int maxConnectionsTotal;
    private final int maxConnectionsPerRoute;
    private final long timeToLive;
    private final ResponseErrorHandler errorHandler;
    private final Integer connectionRequestTimeout;

    public RestTemplateFactory(String environmentName,
                               int maxConnectionsTotal,
                               int maxConnectionsPerRoute,
                               long timeToLive,
                               int connectionRequestTimeout,
                               ResponseErrorHandler errorHandler) {
        this.isProdEnvironment = PRODUCTION_ENVIRONMENT.equalsIgnoreCase(environmentName);
        this.maxConnectionsTotal = maxConnectionsTotal;
        this.maxConnectionsPerRoute = maxConnectionsPerRoute;
        this.timeToLive = timeToLive;
        this.errorHandler = errorHandler;
        this.connectionRequestTimeout = connectionRequestTimeout;
    }

    private static HttpMessageConverter messageConverter() {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
        final MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(mapper);
        return converter;
    }

    public RestTemplate create() throws TechnicalException {
        final RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpClient()));
        restTemplate.getMessageConverters().add(messageConverter());
        restTemplate.setErrorHandler(errorHandler);
        restTemplate.setInterceptors(Collections.singletonList(new RequestResponseLoggingInterceptor()));
        return restTemplate;
    }

    private CloseableHttpClient httpClient() throws TechnicalException {
        HttpClientBuilder httpClientBuilder = HttpClients.custom()
            .setSSLSocketFactory(connectionFactory())
            .setMaxConnTotal(maxConnectionsTotal)
            .setMaxConnPerRoute(maxConnectionsPerRoute)
            .setConnectionTimeToLive(timeToLive, TimeUnit.SECONDS)
            .setRetryHandler(new StandardHttpRequestRetryHandler(3, true));
        if (connectionRequestTimeout != null) {
            httpClientBuilder.setDefaultRequestConfig(RequestConfig.custom().setConnectionRequestTimeout(connectionRequestTimeout).build());
        }
        return httpClientBuilder.build();
    }

    private SSLConnectionSocketFactory connectionFactory() throws TechnicalException {
        if (isProdEnvironment) {
            return new SSLConnectionSocketFactory(SSLContexts.createDefault());
        }
        try {
            final SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, trustAll()).build();
            return new SSLConnectionSocketFactory(sslContext);
        } catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException e) {
            throw new TechnicalException("Error creating SSLContext: " + e.getMessage(), e);
        }
    }

    private TrustStrategy trustAll() {
        return new TrustStrategy() {

            @Override
            public boolean isTrusted(X509Certificate[] arg0, String arg1) {
                return true;
            }
        };
    }

  /*  public void setConnectionRequestTimeout(Integer connectionRequestTimeout) {
        this.connectionRequestTimeout = connectionRequestTimeout;
    }
    */
}

