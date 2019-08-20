package com.luxoft.libraryservice.studio.itunes;

import java.util.List;
import java.util.stream.Collectors;

import com.luxoft.libraryservice.media.model.Media;
import com.luxoft.libraryservice.studio.itunes.json.Album;

public class ItunesAlbumsMapper {

    public static List<Media> from(List<Album> itunesAlbums) {
        return itunesAlbums.stream()
            //public Media(String id, String kind, String title, String subtitle, String description, List<String> authors) {
            .map(album -> new Media(album.getId(), album.getType(), album.getTitle(), album.getCategory(), album.getDescription(), album.getArtists()))
            .collect(Collectors.toList());
    }
}
