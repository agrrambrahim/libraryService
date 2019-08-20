package com.luxoft.libraryservice.media.model;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.luxoft.libraryservice.web.ResponseElementDTO;

@Entity
@Table(name = "owners")
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String fullName;
    @NotNull
    private String kind;

    public Owner() {
    }

    public Owner(String fullName, String kind) {
        this.fullName = fullName;
        this.kind = kind;
    }

    static void writeInto(ResponseElementDTO.ResponseElementBuilder builder, List<Owner> ownerList) {
        if (ownerList.isEmpty() == false) {
            builder.withWritersCategory(ownerList.get(0).kind);
            builder.withWriters(ownerList.stream().map(owner -> owner.fullName).collect(Collectors.toList()));
            return;
        }
    }
}
