package com.luxoft.libraryservice.web;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class ResponseElementDTO implements Serializable {

    private String title;
    @JsonProperty("kind")
    private String category;

    public ResponseElementDTO(String title, String category) {
        this.title = title;
        this.category = category;
    }

    public String getTitle() {
        return this.title;
    }

    public String getCategory() {
        return this.category;
    }

    public static class BookResponseElementDTO extends ResponseElementDTO {
        @JsonProperty("Authors")
        private List<String> authors;

        public List<String> getAuthors() {
            return this.authors;
        }

        public BookResponseElementDTO(String title, String category, List<String> authors) {
            super(title, category);
            this.authors = authors;
        }
    }

    public static class AlbumResponseElementDTO extends ResponseElementDTO {
        @JsonProperty("Artists")
        private List<String> artists;

        public List<String> getArtists() {
            return this.artists;
        }

        public AlbumResponseElementDTO(String title, String category, List<String> artists) {
            super(title, category);
            this.artists = artists;
        }
    }

    public static class ResponseElementBuilder {

        private static final String BOOK_CATEGORY_NAME = "book";
        private static final String ALBUM_CATEGORY_NAME = "Album";

        private String title;
        private String category;
        private List<String> writers;
        private String writersCategory;

        public ResponseElementBuilder withTitle(String title) {
            this.title = title;
            return this;
        }

        public ResponseElementBuilder withCategory(String category) {

            if (category.toLowerCase().startsWith(BOOK_CATEGORY_NAME)) {
                this.category = BOOK_CATEGORY_NAME;
            } else {
                this.category = ALBUM_CATEGORY_NAME;
            }
            return this;
        }

        public ResponseElementBuilder withWritersCategory(String writersCategory) {
            this.writersCategory = writersCategory;
            return this;
        }

        public ResponseElementBuilder withWriters(List<String> writers) {
            this.writers = writers;
            return this;
        }

        public ResponseElementDTO build() {
            if (this.category.equals(BOOK_CATEGORY_NAME)) {
                return new BookResponseElementDTO(this.title, this.category, this.writers);
            } else {
                return new AlbumResponseElementDTO(this.title, this.category, this.writers);
            }
        }

        ;
    }
}