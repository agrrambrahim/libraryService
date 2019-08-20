package com.luxoft.libraryservice.studio.itunes.json;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Album {
    /*
      {
          "amgArtistId": 468749,
          "artistId": 909253,
          "artistName": "Jack Johnson",
          "artistViewUrl": "https://music.apple.com/us/artist/jack-johnson/909253?uo=4",
          "artworkUrl100": "https://is2-ssl.mzstatic.com/image/thumb/Music118/v4/24/46/97/24469731-f56f-29f6-67bd-53438f59ebcb/source/100x100bb.jpg",
          "artworkUrl60": "https://is2-ssl.mzstatic.com/image/thumb/Music118/v4/24/46/97/24469731-f56f-29f6-67bd-53438f59ebcb/source/60x60bb.jpg",
          "collectionCensoredName": "In Between Dreams (Bonus Track Version)",
          "collectionExplicitness": "notExplicit",
          "collectionId": 1440857781,
          "collectionName": "In Between Dreams (Bonus Track Version)",
          "collectionPrice": 6.99,
          "collectionType": "Album",
          "collectionViewUrl": "https://music.apple.com/us/album/in-between-dreams-bonus-track-version/1440857781?uo=4",
          "copyright": "℗ 2013 Jack Johnson",
          "country": "USA",
          "currency": "USD",
          "primaryGenreName": "Rock",
          "releaseDate": "2005-03-01T08:00:00Z",
          "trackCount": 16,
          "wrapperType": "collection"
        },
     */
    @JsonProperty("collectionId")
    private String id;
    @JsonProperty("collectionName")
    private String title;
    @JsonProperty("collectionCensoredName")
    private String description;
    @JsonProperty("primaryGenreName")
    private String category;
    @JsonProperty("collectionType")
    private String type;
    @JsonProperty("artistName")
    private String artists;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getArtists() {
        return Arrays.asList(artists.split(","));
    }

    public void setArtists(String artists) {
        this.artists = artists;
    }
}
