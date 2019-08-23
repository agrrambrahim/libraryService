package com.luxoft.libraryservice.upstream.client.itunes.endpoint;

import java.net.URI;

import org.springframework.http.ResponseEntity;

import com.luxoft.libraryservice.exceptions.NotAvailableResultsException;
import com.luxoft.libraryservice.rest.factory.RestRequest;
import com.luxoft.libraryservice.rest.factory.RestRequestFactory;
import com.luxoft.libraryservice.upstream.client.itunes.json.AlbumsWrapper;

public class ItunesApiRestClient implements ItunesApiClient {
    public static final String RESPONSE_TIME_IN_SECONDS = "responseTimeInSeconds";
    public static final int RESPONSE_TIME_ATTRIBUTE_INDEX = 0;

    private final ItunesEndpointsFactory endpointsFactory;
    private final RestRequestFactory restRequestFactory;

    public ItunesApiRestClient(ItunesEndpointsFactory googleBooksEndpointsFactory,
                               RestRequestFactory restRequestFactory) {
        this.endpointsFactory = googleBooksEndpointsFactory;
        this.restRequestFactory = restRequestFactory;
    }

    @Override
    public AlbumsWrapper retrieve(String terms, int resultLimits) {

        final URI endpoint = endpointsFactory.getAlbumsWithMaxResult(terms, resultLimits);
        final RestRequest<AlbumsWrapper> request = restRequestFactory.create(endpoint, AlbumsWrapper.class);
        try {

            ResponseEntity<AlbumsWrapper> restResponse = request.invokeGetRequest();
            AlbumsWrapper upStreamResponse = restResponse.getBody();
            upStreamResponse.setResponseTime(extractResponseTimeFrom(restResponse));

            return upStreamResponse;
        } catch (Exception exception) {
            throw new NotAvailableResultsException("Cannot load Books for query=" + terms + ". due to" + exception.getMessage(), exception);
        }
    }

    private String extractResponseTimeFrom(ResponseEntity<AlbumsWrapper> restResponse) {
        return restResponse.getHeaders().get(RESPONSE_TIME_IN_SECONDS).get(RESPONSE_TIME_ATTRIBUTE_INDEX);
    }
}
