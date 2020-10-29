package com.nxastudios.acetato.core.infrastructure.services.converter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nxastudios.acetato.core.domain.Artist;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.bson.codecs.pojo.annotations.BsonId;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ArtistDTO implements Serializable {
    private static final String ARTIST_ID = "_id";
    private static final String NAME = "name";

    @JsonProperty(NAME)
    private String name;

    @BsonId
    @JsonProperty(ARTIST_ID)
    private String artistId;

    @JsonCreator
    public ArtistDTO(@BsonId @JsonProperty(ARTIST_ID) String artistId, @JsonProperty(NAME) String name) {
        this.artistId = artistId;
        this.name = name;
    }

    @JsonCreator
    public ArtistDTO() {

    }

    public static List<ArtistDTO> buildFrom(List<Artist> artists) {
        return artists.stream()
                .map(artist -> ArtistDTO.buildFrom(artist))
                .collect(Collectors.toList());
    }

    public static List<ArtistDTO> buildFrom(JsonArray artists) {
        return artists
                .stream()
                .map(i -> ArtistDTO.buildFrom(((JsonObject) i)))
                .collect(Collectors.toList());
    }

    public static ArtistDTO buildFrom(Artist artist) {
        return new ArtistDTO(artist.getArtistId(), artist.getName());
    }

    public static ArtistDTO buildFrom(JsonObject json) {
        return new ArtistDTO(json.getString(ARTIST_ID, ""), json.getString(NAME, ""));
    }

    public static List<Artist> mapArtistsFrom(List<ArtistDTO> artists) {
        if (artists == null) return new ArrayList();
        return artists.stream()
                .map(artistDTO -> new Artist(artistDTO))
                .collect(Collectors.toList());
    }

    @BsonId
    public String id() {
        return artistId;
    }

    public String name() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArtistDTO artistDTO = (ArtistDTO) o;
        return Objects.equals(name, artistDTO.name) &&
                Objects.equals(artistId, artistDTO.artistId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, artistId);
    }
}
