package com.luxoft.libraryservice.upstream.client.googlebooks.endpoint;

import java.net.URI;

import org.springframework.http.ResponseEntity;

import com.luxoft.libraryservice.exceptions.NotAvailableResultsException;
import com.luxoft.libraryservice.rest.factory.RestRequest;
import com.luxoft.libraryservice.rest.factory.RestRequestFactory;
import com.luxoft.libraryservice.upstream.client.googlebooks.json.VolumeWrapper;

public class GoogleBooksApiRestClient implements GoogleBooksApiClient {
    private static final String RESPONSE_TIME_IN_SECONDS = "responseTimeInSeconds";

    private final GoogleBooksEndpointsFactory endpointsFactory;
    private final RestRequestFactory restRequestFactory;

    public GoogleBooksApiRestClient(GoogleBooksEndpointsFactory googleBooksEndpointsFactory,
                                    RestRequestFactory restRequestFactory) {
        this.endpointsFactory = googleBooksEndpointsFactory;
        this.restRequestFactory = restRequestFactory;
    }

    @Override
    public VolumeWrapper retrieve(String terms, int resultLimits) {

        final URI endpoint = endpointsFactory.buildEndpointURLFor(terms, resultLimits);
        final RestRequest<VolumeWrapper> request = restRequestFactory.create(endpoint, VolumeWrapper.class);
        try {
            ResponseEntity<VolumeWrapper> restResponse = request.invokeGetRequest();
            VolumeWrapper upStreamResponse = restResponse.getBody();
            upStreamResponse.setResponseTime(retrieveResponseTimeFrom(restResponse)); //Actually the API response should not be modified
            // todo : create a new DTO to wrap the api response + responseTime
            return upStreamResponse;
        } catch (Exception exception) {
            throw new NotAvailableResultsException("Cannot load Albums for query=" + terms + ". due to" + exception.getMessage(), exception);
        }
    }

    private String retrieveResponseTimeFrom(ResponseEntity<VolumeWrapper> restResponse) {
        return restResponse.getHeaders().get(RESPONSE_TIME_IN_SECONDS).get(0);
    }
}
