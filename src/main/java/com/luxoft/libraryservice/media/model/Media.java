package com.luxoft.libraryservice.media.model;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.google.common.collect.Lists;
import com.luxoft.libraryservice.web.dto.ResponseElementDTO;

@Entity
@Table(name = "medias")
public class Media {
    private static final String BOOK_CATEGORY_NAME = "Book";
    private static final String AUTHOR_CATEGORY_NAME = "Author";
    private static final String ARTIST_CATEGORY_NAME = "Artist";
    @Id
    private String id;
    @NotNull
    private String kind;
    @NotNull
    private String title;

    private String subtitle;

    @Column(length = 10485760)
    private String description;
    @OneToMany(cascade = {CascadeType.ALL})
    private List<Owner> owners;

    public Media() {
        this.owners = Lists.newArrayList();
        this.id = "";
        this.kind = "";
        this.description = "";
        this.subtitle = "";
    }

    public Media(String id, String kind, String title, String subtitle, String description, List<String> authors) {
        this.id = id;
        this.kind = kind;
        this.title = title;
        this.subtitle = subtitle;
        this.description = description;
        String ownerCategory = kind.equalsIgnoreCase(BOOK_CATEGORY_NAME) ? AUTHOR_CATEGORY_NAME : ARTIST_CATEGORY_NAME;
        this.owners = authors.stream().map(ownerName -> new Owner(ownerName, ownerCategory)).collect(Collectors.toList());
    }

    public void writeInto(ResponseElementDTO.ResponseElementBuilder responseElementBuilder) {
        responseElementBuilder.withTitle(this.title).withCategory(this.kind);
        Owner.writeInto(responseElementBuilder, this.owners);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Media that = (Media) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
