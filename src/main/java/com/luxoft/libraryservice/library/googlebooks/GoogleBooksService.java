package com.luxoft.libraryservice.library.googlebooks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luxoft.libraryservice.library.googlebooks.endpoint.BooksRetriver;
import com.luxoft.libraryservice.library.googlebooks.json.VolumeWrapper;
import com.luxoft.libraryservice.media.SearchResult;
import com.luxoft.libraryservice.rest.exceptions.NotAvailableResultsException;

@Service
public class GoogleBooksService implements BooksService {

    @Autowired
    private BooksRetriver booksRetriver;

    @Override
    public SearchResult searchBy(String terms, int resultLimits) throws NotAvailableResultsException {
        VolumeWrapper jsonResponse = booksRetriver.retrieve(terms, resultLimits);
        return GoogleBooksMapper.mapResults(jsonResponse);
    }
}
