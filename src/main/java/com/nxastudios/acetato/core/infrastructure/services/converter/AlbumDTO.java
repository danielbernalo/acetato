package com.nxastudios.acetato.core.infrastructure.services.converter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nxastudios.acetato.core.domain.Album;
import com.nxastudios.acetato.core.domain.AlbumType;
import io.vertx.core.json.JsonObject;
import org.bson.codecs.pojo.annotations.BsonId;

import java.util.List;

public class AlbumDTO {
    @BsonId
    @JsonProperty("_id")
    private String albumId;

    @JsonProperty("title")
    private String title;

    @JsonProperty("release_date")
    private Long releaseDate;

    @JsonProperty("artists")
    private List<ArtistDTO> artists;

    @JsonProperty("tracks")
    private List<TrackDTO> tracks;

    @JsonProperty("type")
    private AlbumType type;

    @JsonCreator
    public AlbumDTO(@BsonId @JsonProperty("_id") String albumId, @JsonProperty("title") String title, @JsonProperty("release_date") Long releaseDate, @JsonProperty("artists") List<ArtistDTO> artists, @JsonProperty("tracks") List<TrackDTO> tracks, @JsonProperty("type") AlbumType type) {

        this.albumId = albumId;
        this.title = title;
        this.releaseDate = releaseDate;
        this.artists = artists;
        this.tracks = tracks;
        this.type = type;
    }

    public static AlbumDTO buildFrom(Album album) {
        return new AlbumDTO(album.getAlbumId(), album.getTitle(), album.getReleaseDate(), ArtistDTO.buildFrom(album.getArtists()), TrackDTO.buildFrom(album.getTracks()), album.getType());
    }

    public static AlbumDTO buildFrom(JsonObject json) {
        return new AlbumDTO(
                json.getString("_id"),
                json.getString("title"),
                json.getLong("release_date"),
                ArtistDTO.buildFrom(json.getJsonArray("artists")),
                TrackDTO.buildFrom(json.getJsonArray("tracks")),
                AlbumType.valueOf(json.getString("type"))
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
