package com.luxoft.libraryservice.library.googlebooks.json;

import java.util.List;

import org.apache.logging.log4j.util.Strings;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Book {
    @JsonProperty("id")
    private String id;
    @JsonProperty("kind")
    private String kind;
    private String title;
    private String subtitle;
    private String description;
    private List<String> authors;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return Strings.isNotEmpty(subtitle) ? subtitle : "";
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getDescription() {
        return Strings.isNotEmpty(description) ? description : "";
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getAuthors() {
        return (authors == null) ? Lists.newArrayList() : authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }
}
