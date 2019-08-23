package com.luxoft.libraryservice.upstream.client;

import com.luxoft.libraryservice.exceptions.NotAvailableResultsException;
import com.luxoft.libraryservice.searchservice.dao.SearchResult;

public interface UpStreamClient {

    SearchResult fetchForMediasBy(String terms, int resultLimits) throws NotAvailableResultsException;
    String getServedMediaCategory();
}
