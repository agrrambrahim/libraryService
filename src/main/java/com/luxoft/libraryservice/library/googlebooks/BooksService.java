package com.luxoft.libraryservice.library.googlebooks;

import java.util.List;

import com.luxoft.libraryservice.media.MediaService;
import com.luxoft.libraryservice.media.SearchResult;
import com.luxoft.libraryservice.media.model.Media;
import com.luxoft.libraryservice.rest.exceptions.NotAvailableResultsException;

public interface BooksService extends MediaService {

    SearchResult searchBy(String terms, int resultLimits) throws NotAvailableResultsException;
}
