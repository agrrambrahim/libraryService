package com.luxoft.libraryservice.searchservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.luxoft.libraryservice.exceptions.NotAvailableResultsException;
import com.luxoft.libraryservice.exceptions.TechnicalException;
import com.luxoft.libraryservice.searchservice.dao.MediaDao;
import com.luxoft.libraryservice.searchservice.dao.SearchResult;
import com.luxoft.libraryservice.searchservice.dto.factory.ResponseBuilder;
import com.luxoft.libraryservice.upstream.client.UpStreamClient;
import com.luxoft.libraryservice.web.dto.Response;
//the main application service; responsible to get data from the upstream and parse to DTO

public class DefaultSearchService implements SearchService {
    @Value("${com.luxoft.upstream.responselimit}")
    private int maxResult;
    @Autowired
    private MediaDao mediaDao;
    @Autowired
    private List<UpStreamClient> upStreamClientList;

    @Override
    public Response searchForMediaBy(String searchTerm) {

        ResponseBuilder responseBuilder = new ResponseBuilder(this.maxResult);

        for (UpStreamClient upstream : upStreamClientList) {
            String servedMediaCategory = upstream.getServedMediaCategory();
            SearchResult searchResult = mediaDao.getMediasFromDatabaseByTermAndCategory(searchTerm, servedMediaCategory, maxResult);
            try {
                searchResult = addMediasFromUpStreamIfNeeded(searchTerm, upstream, searchResult);
            } catch (NotAvailableResultsException | TechnicalException exception) {
                responseBuilder.addErrorMessage(exception.getCause(), servedMediaCategory);
            } finally {
                responseBuilder.addSearchResult(searchResult);
            }
        }
        return responseBuilder.build();
    }

    private SearchResult addMediasFromUpStreamIfNeeded(String input,
                                                       UpStreamClient upstream,
                                                       SearchResult databaseSearchResult) {
        SearchResult searchResult = databaseSearchResult;
        if (searchResult.getSearchResults().size() >= maxResult) {
            return searchResult;
        } else {
            searchResult = upstream.fetchForMediasBy(input, maxResult); // prioritize upstream medias over the existing ones in our database,
            mediaDao.saveResultInDataBase(searchResult); // this will help to populate our database and increase performance when upstream be down
            return searchResult;
        }
    }
}
