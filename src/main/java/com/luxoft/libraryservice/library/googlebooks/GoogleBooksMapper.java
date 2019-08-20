package com.luxoft.libraryservice.library.googlebooks;

import java.util.List;
import java.util.stream.Collectors;

import com.luxoft.libraryservice.library.googlebooks.json.Book;
import com.luxoft.libraryservice.library.googlebooks.json.VolumeWrapper;
import com.luxoft.libraryservice.media.SearchResult;
import com.luxoft.libraryservice.media.model.Media;

class GoogleBooksMapper {
    private static final String BOOK_CATEGORY_NAME = "book";
    private static final String ALBUM_CATEGORY_NAME = "Album";
    private static final String BOOK_WRITER_CATEGORY_NAME = "Owner";
    private static final String ALBUM_WRITER_CATEGORY_NAME = "Artist";
    private static final String UPSTREAM_NAME = "Google Books API";

    static SearchResult mapResults(VolumeWrapper jsonResponse){
        return new SearchResult(from(jsonResponse.getBooks()), UPSTREAM_NAME, jsonResponse.getResponseTime());
    }

    static List<Media> from(List<Book> googleBooks) {
        return googleBooks.stream()
            .map(googleBook -> new Media(googleBook.getId(), BOOK_CATEGORY_NAME, googleBook.getTitle(), googleBook.getSubtitle(), googleBook.getDescription()
                , googleBook.getAuthors()))
            .collect(Collectors.toList());
    }
}
