package com.luxoft.libraryservice.studio.itunes.endpoint;

import java.net.URI;
import java.util.List;

import org.springframework.web.client.HttpStatusCodeException;

import com.luxoft.libraryservice.rest.exceptions.NotAvailableResultsException;
import com.luxoft.libraryservice.rest.RestRequest;
import com.luxoft.libraryservice.rest.RestRequestFactory;
import com.luxoft.libraryservice.studio.itunes.json.Album;
import com.luxoft.libraryservice.studio.itunes.json.AlbumsWrapper;

public class ItunesAlbumsRetriver implements AlbumsRetriver {

private final ItunesEndpointsFactory endpointsFactory;
private final RestRequestFactory restRequestFactory;

    public ItunesAlbumsRetriver(ItunesEndpointsFactory googleBooksEndpointsFactory,
                               RestRequestFactory restRequestFactory) {
        this.endpointsFactory = googleBooksEndpointsFactory;
        this.restRequestFactory = restRequestFactory;
    }

    @Override
    public List<Album> retrieve(String terms, int resultLimits) throws NotAvailableResultsException {

        final URI endpoint = endpointsFactory.getAlbumsWithMaxResult(terms, resultLimits);
        final RestRequest<AlbumsWrapper> request = restRequestFactory.create(endpoint, AlbumsWrapper.class);
        try {
            return request.invokeGet().getResults();
        } catch (HttpStatusCodeException e) {
            throw new NotAvailableResultsException("Cannot load Albums for query=" + terms + ": " + e.getMessage(), e);
        }
    }
}
