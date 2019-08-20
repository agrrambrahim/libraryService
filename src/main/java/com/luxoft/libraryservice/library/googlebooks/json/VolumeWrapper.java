package com.luxoft.libraryservice.library.googlebooks.json;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VolumeWrapper {
    @JsonProperty("totalItems")
    private int totalItems;
    @JsonProperty("items")
    private List<BookWrapper> bookWrappers = Lists.newArrayList();

    private float responseTime;

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public List<Book> getBooks() {
        List<Book> books = Lists.newArrayList();
        for (BookWrapper wrapper : bookWrappers) {
            Book book = wrapper.getBook();
            book.setId(wrapper.getId());
            book.setKind(wrapper.getKind());
            books.add(wrapper.getBook());
        }
        return books;
    }

    public void setBooks(List<BookWrapper> books) {
        this.bookWrappers = Lists.newArrayList(books);
    }

    public void setResponseTime(String responseTime){
        this.responseTime = Float.valueOf(responseTime);
    }

    public float getResponseTime() {
        return responseTime;
    }
}
