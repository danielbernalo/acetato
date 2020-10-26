package com.nxastudios.acetato.core.infrastructure.repositories.services.converter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nxastudios.acetato.core.domain.Artist;
import io.vertx.core.json.JsonObject;
import org.bson.codecs.pojo.annotations.BsonId;

import java.util.Objects;

public class ArtistDTO {

    @JsonProperty("name")
    private String name;

    @BsonId
    @JsonProperty("_id")
    private String artistId;

    @JsonCreator
    public ArtistDTO(@BsonId @JsonProperty("_id") String artistId, @JsonProperty("name") String name) {
        this.artistId = artistId;
        this.name = name;
    }

    public String name() {
        return name;
    }

    @BsonId
    public String id() {
        return artistId;
    }

    public static ArtistDTO buildFrom(Artist artist) {
        return new ArtistDTO(artist.getArtistId(), artist.getName());
    }

    public static ArtistDTO buildFrom(JsonObject json) {
        return new ArtistDTO(json.getString("_id"), json.getString("name"));
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
