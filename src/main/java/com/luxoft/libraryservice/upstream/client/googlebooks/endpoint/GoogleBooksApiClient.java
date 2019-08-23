package com.luxoft.libraryservice.upstream.client.googlebooks.endpoint;

import com.luxoft.libraryservice.upstream.client.googlebooks.json.VolumeWrapper;

public interface GoogleBooksApiClient {
    // todo : return type should be a custom DTO not jsonWrapper
    // if it not the case remove this interface, and couple restClient directly to googleBooksService
    VolumeWrapper retrieve(String terms, int resultLimits);
}
