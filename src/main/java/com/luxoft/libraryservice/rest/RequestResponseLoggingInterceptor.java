package com.luxoft.libraryservice.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Date;

public class RequestResponseLoggingInterceptor implements ClientHttpRequestInterceptor {

    private static final String RESPONSE_TIME_IN_SECONDS = "responseTimeInSeconds";

    private long requestDate;
    private long responseDate;

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

        this.requestDate = System.currentTimeMillis();
        ClientHttpResponse response = execution.execute(request, body);
        this.responseDate =System.currentTimeMillis();
        float sec = (responseDate - requestDate) / 1000F;
        response.getHeaders().add(RESPONSE_TIME_IN_SECONDS, String.valueOf(sec));
        return response;
    }
}
