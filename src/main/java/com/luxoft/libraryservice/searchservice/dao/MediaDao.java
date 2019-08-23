package com.luxoft.libraryservice.searchservice.dao;

import org.springframework.beans.factory.annotation.Autowired;

import com.luxoft.libraryservice.media.repository.MediaRepository;

public class MediaDao {
    private static final String UPSTREAM_NAME = "Local Database";

    @Autowired
    private MediaRepository mediaRepository;

public SearchResult getMediasFromDatabaseByTermAndCategory(String term, String category, int maxResults){
switch (category){
    case "Book" : return getBooksFromDatabase(term, maxResults);
    case "Album" : return getAlbumsFromDatabase(term, maxResults);
    default:
    //    throw new TechnicalException("The category :"+category+" is not registrated in Media Dao");
        return new SearchResult(null,null,0);
}
}

    private SearchResult getAlbumsFromDatabase(String input, int limit) {
        return new SearchResult(mediaRepository.findAlbumsbyTerm("%" + input.toUpperCase() + "%", limit),UPSTREAM_NAME, Float.NaN) ;
    }

    private SearchResult getBooksFromDatabase(String input, int limit) {

        return new SearchResult(mediaRepository.findBooksbyTerm("%" + input.toUpperCase() + "%", limit),UPSTREAM_NAME, Float.NaN);
    }

    public void saveResultInDataBase(SearchResult searchResult) {
        mediaRepository.saveAll(searchResult.getSearchResults());
    }

}
