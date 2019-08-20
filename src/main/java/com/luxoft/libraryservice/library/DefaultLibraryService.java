package com.luxoft.libraryservice.library;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luxoft.libraryservice.library.googlebooks.BooksService;
import com.luxoft.libraryservice.library.setup.ResponseBuilder;
import com.luxoft.libraryservice.media.MediaService;
import com.luxoft.libraryservice.media.SearchResult;
import com.luxoft.libraryservice.media.model.Media;
import com.luxoft.libraryservice.media.repository.MediaRepository;
import com.luxoft.libraryservice.rest.exceptions.NotAvailableResultsException;
import com.luxoft.libraryservice.web.Response;

@Service
public class DefaultLibraryService implements LibraryService {
    // @Value("${com.libraryservice.result.response.limit:5}")
    private int maxResult = 5;

    @Autowired
    private MediaRepository mediaRepository;
    @Autowired
    private BooksService booksService;
    @Autowired
    private com.luxoft.libraryservice.studio.itunes.AlbumsService albumsService;

    @Override
    public Response searchForMediaBy(String input) {
        ResponseBuilder responseBuilder = new ResponseBuilder();
        List<Media> storedBooks = getBooksFromDatabase(input, maxResult);
        List<Media> storedAlbums = getAlbumsFromDatabase(input, maxResult);

        getMediasFromUpStreamIfNeeded(input, responseBuilder, booksService, storedBooks);
        getMediasFromUpStreamIfNeeded(input, responseBuilder, albumsService, storedAlbums);
        return responseBuilder.build();
    }

    private void getMediasFromUpStreamIfNeeded(String input, ResponseBuilder responseBuilder, MediaService mediaService, List<Media> storedMedias) {
        if (storedMedias.size() < maxResult) {
            try {
                SearchResult searchResult = booksService.searchBy(input, maxResult);
                responseBuilder.addSearchResult(searchResult);
                saveResultInDataBase(searchResult);
            } catch (NotAvailableResultsException exception) {
                responseBuilder.addResults(storedMedias);
            }
        }
    }

    private List<Media> getAlbumsFromDatabase(String input, int limit) {
        return mediaRepository.findAlbumsbyTerm("%" + input.toUpperCase() + "%", limit);
    }

    private List<Media> getBooksFromDatabase(String input, int limit) {

        return mediaRepository.findBooksbyTerm("%" + input.toUpperCase() + "%", limit);
    }

    private void saveResultInDataBase(SearchResult searchResult) {
        mediaRepository.saveAll(searchResult.getSearchResults());
    }
}
