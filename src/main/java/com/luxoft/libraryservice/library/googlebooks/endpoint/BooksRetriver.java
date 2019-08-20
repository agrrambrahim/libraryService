package com.luxoft.libraryservice.library.googlebooks.endpoint;

import java.util.List;

import com.luxoft.libraryservice.library.googlebooks.json.VolumeWrapper;
import com.luxoft.libraryservice.rest.exceptions.NotAvailableResultsException;
import com.luxoft.libraryservice.library.googlebooks.json.Book;

public interface BooksRetriver {

    VolumeWrapper retrieve(String terms, int resultLimits) throws NotAvailableResultsException;
}
