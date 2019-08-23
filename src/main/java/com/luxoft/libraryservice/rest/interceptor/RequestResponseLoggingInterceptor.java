package com.luxoft.libraryservice.rest.interceptor;

import java.io.IOException;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import com.luxoft.libraryservice.exceptions.TechnicalException;

public class RequestResponseLoggingInterceptor implements ClientHttpRequestInterceptor {

    private static final String RESPONSE_TIME_IN_SECONDS = "responseTimeInSeconds";

    private long requestDate;
    private long responseDate;

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) {
        try{
            this.requestDate = System.currentTimeMillis();
            ClientHttpResponse response = execution.execute(request, body);
            this.responseDate = System.currentTimeMillis();
            float sec = (responseDate - requestDate) / 1000F;
            response.getHeaders().add(RESPONSE_TIME_IN_SECONDS, String.valueOf(sec));
            return response;
        }catch (IOException exception){
            throw new TechnicalException(exception.getMessage(),exception);
        }
    }
}
