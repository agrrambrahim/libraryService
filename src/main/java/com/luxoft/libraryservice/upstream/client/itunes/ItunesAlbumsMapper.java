package com.luxoft.libraryservice.upstream.client.itunes;

import java.util.List;
import java.util.stream.Collectors;

import com.luxoft.libraryservice.searchservice.dao.SearchResult;
import com.luxoft.libraryservice.media.model.Media;
import com.luxoft.libraryservice.upstream.client.itunes.json.Album;
import com.luxoft.libraryservice.upstream.client.itunes.json.AlbumsWrapper;

public class ItunesAlbumsMapper {

    public static List<Media> from(List<Album> itunesAlbums) {
        return itunesAlbums.stream()
        //    .map(album -> new Media(album.getId(), album.getType(), album.getTitle(), album.getCategory(), album.getDescription(), album.getArtists()))
            .map(album -> new Media(album.getId(), ItunesAlbumsService.SERVED_MEDIA_CATEGORY_NAME, album.getTitle(), album.getCategory(), album.getDescription(), album.getArtists()))
            .collect(Collectors.toList());
    }

    public static SearchResult mapResults(AlbumsWrapper jsonResponse) {
        return new SearchResult(from(jsonResponse.getResults()), ItunesAlbumsService.UPSTREAM_NAME, jsonResponse.getResponseTime());
    }
}
