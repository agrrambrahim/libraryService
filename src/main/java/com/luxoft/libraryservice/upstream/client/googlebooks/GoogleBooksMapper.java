package com.luxoft.libraryservice.upstream.client.googlebooks;

import java.util.List;
import java.util.stream.Collectors;

import com.luxoft.libraryservice.upstream.client.googlebooks.json.Book;
import com.luxoft.libraryservice.upstream.client.googlebooks.json.VolumeWrapper;
import com.luxoft.libraryservice.searchservice.dao.SearchResult;
import com.luxoft.libraryservice.media.model.Media;

class GoogleBooksMapper {

    static SearchResult mapResults(VolumeWrapper jsonResponse){
        return new SearchResult(from(jsonResponse.getBooks()), GoogleBooksService.UPSTREAM_NAME, jsonResponse.getResponseTime());
    }

    static List<Media> from(List<Book> googleBooks) {
        return googleBooks.stream()
            .map(googleBook -> new Media(googleBook.getId(), GoogleBooksService.SERVED_MEDIA_CATEGORY_NAME, googleBook.getTitle(), googleBook.getSubtitle(), googleBook.getDescription()
                , googleBook.getAuthors()))
            .collect(Collectors.toList());
    }
}
