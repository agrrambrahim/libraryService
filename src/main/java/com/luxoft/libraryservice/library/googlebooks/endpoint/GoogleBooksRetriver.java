package com.luxoft.libraryservice.library.googlebooks.endpoint;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;

import com.luxoft.libraryservice.rest.exceptions.NotAvailableResultsException;
import com.luxoft.libraryservice.library.googlebooks.json.VolumeWrapper;
import com.luxoft.libraryservice.rest.RestRequest;
import com.luxoft.libraryservice.rest.RestRequestFactory;

public class GoogleBooksRetriver implements BooksRetriver {
    public static final String RESPONSE_TIME_IN_SECONDS = "responseTimeInSeconds";

    private final GoogleBooksEndpointsFactory endpointsFactory;
    private final RestRequestFactory restRequestFactory;

    public GoogleBooksRetriver(GoogleBooksEndpointsFactory googleBooksEndpointsFactory,
                               RestRequestFactory restRequestFactory) {
        this.endpointsFactory = googleBooksEndpointsFactory;
        this.restRequestFactory = restRequestFactory;
    }

    @Override
    public VolumeWrapper retrieve(String terms, int resultLimits) throws NotAvailableResultsException {

        final URI endpoint = endpointsFactory.buildEndpointURLFor(terms, resultLimits);
        final RestRequest<VolumeWrapper> request = restRequestFactory.create(endpoint, VolumeWrapper.class);
        try {
            
            ResponseEntity<VolumeWrapper> restResponse = request.invokeGetRequest();
            VolumeWrapper upStreamResponse = restResponse.getBody();
            upStreamResponse.setResponseTime(restResponse.getHeaders().get(RESPONSE_TIME_IN_SECONDS).get(0));

            return upStreamResponse;
        } catch (HttpStatusCodeException e) {
            throw new NotAvailableResultsException("Cannot load Books for query=" + terms + ": " + e.getMessage(), e);
        }
    }
}
