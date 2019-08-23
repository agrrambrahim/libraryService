package com.luxoft.libraryservice.upstream.client.itunes.endpoint;

import com.luxoft.libraryservice.upstream.client.itunes.json.AlbumsWrapper;

public interface ItunesApiClient {

    AlbumsWrapper retrieve(String terms, int resultLimits);
}
