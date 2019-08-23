package com.luxoft.libraryservice.media.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.luxoft.libraryservice.media.model.Media;

public interface MediaRepository extends JpaRepository<Media, String> {

    @Query(value = "FROM Media c WHERE upper(c.kind) LIKE 'ALBUM' and (upper(c.title) LIKE ?1 or upper(c.description)  LIKE ?1 or upper(c.subtitle) LIKE ?1 )")
    List<Media> findAlbumsbyTerm(String term, int limit);

    @Query(value = "FROM Media c WHERE upper(c.kind) LIKE 'BOOK' and (upper(c.title) LIKE ?1 or upper(c.description)  LIKE ?1 or upper(c.subtitle) LIKE ?1 )")
    List<Media> findBooksbyTerm(String term, int limit);
}
