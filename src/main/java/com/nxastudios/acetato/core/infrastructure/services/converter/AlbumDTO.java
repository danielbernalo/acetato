package com.nxastudios.acetato.core.infrastructure.services.converter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nxastudios.acetato.core.domain.Album;
import com.nxastudios.acetato.core.domain.AlbumType;
import io.vertx.core.json.JsonObject;
import org.bson.codecs.pojo.annotations.BsonId;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AlbumDTO implements Serializable {

    private static final String ALBUM_ID = "_id";
    private static final String TITLE = "title";
    private static final String RELEASE_DATE = "release_date";
    private static final String ARTISTS = "artists";
    private static final String TRACKS = "tracks";
    private static final String ALBUM_TYPE = "type";


    @BsonId
    @JsonProperty(ALBUM_ID)
    private String albumId;

    @JsonProperty(TITLE)
    private String title;

    @JsonProperty(RELEASE_DATE)
    private Long releaseDate;

    @JsonProperty(ARTISTS)
    private List<ArtistDTO> artists;

    @JsonProperty(TRACKS)
    private List<TrackDTO> tracks;

    @JsonProperty(ALBUM_TYPE)
    private AlbumType type;

    @JsonCreator
    public AlbumDTO(@BsonId @JsonProperty(ALBUM_ID) String albumId, @JsonProperty(TITLE) String title, @JsonProperty(RELEASE_DATE) Long releaseDate, @JsonProperty(ARTISTS) List<ArtistDTO> artists, @JsonProperty(TRACKS) List<TrackDTO> tracks, @JsonProperty(ALBUM_TYPE) AlbumType type) {

        this.albumId = albumId;
        this.title = title;
        this.releaseDate = releaseDate;
        this.artists = artists;
        this.tracks = tracks;
        this.type = type;
    }

    @JsonCreator
    public AlbumDTO() {

    }

    public static AlbumDTO buildFrom(Album album) {
        return new AlbumDTO(album.getAlbumId(), album.getTitle(), album.getReleaseDate(), ArtistDTO.buildFrom(album.getArtists()), TrackDTO.buildFrom(album.getTracks()), album.getType());
    }

    public static AlbumDTO buildFrom(JsonObject json) {
        String id = json.getString(ALBUM_ID) != null ? json.getString(ALBUM_ID) : "";
        List<ArtistDTO> artists = json.getJsonArray(ARTISTS) != null ? ArtistDTO.buildFrom(json.getJsonArray(ARTISTS)) : new ArrayList();
        List<TrackDTO> tracks = json.getJsonArray(TRACKS) != null ? TrackDTO.buildFrom(json.getJsonArray(TRACKS)) : new ArrayList();
        return new AlbumDTO(
                id,
                json.getString(TITLE, ""),
                json.getLong(RELEASE_DATE, 0L),
                artists,
                tracks,
                AlbumType.valueOf(json.getString(ALBUM_TYPE, "ALBUM"))
        );

    }

    public String title() {
        return title;
    }

    public Long releaseDate() {
        return releaseDate;
    }

    public List<ArtistDTO> artists() {
        return artists;
    }

    public List<TrackDTO> tracks() {
        return tracks;
    }

    public AlbumType type() {
        return type;
    }

    @BsonId
    public String id() {
        return albumId;
    }
}
