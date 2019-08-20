package com.luxoft.libraryservice.studio.itunes.endpoint;

import java.util.List;

import com.luxoft.libraryservice.rest.exceptions.NotAvailableResultsException;

import com.luxoft.libraryservice.studio.itunes.json.Album;

public interface AlbumsRetriver {

    List<Album> retrieve(String terms, int resultLimits) throws NotAvailableResultsException;

}
