package com.nxastudios.acetato.core.infrastructure.repositories.services.converter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.nxastudios.acetato.core.domain.Artist;
import io.vertx.core.json.JsonObject;
import org.bson.codecs.pojo.annotations.BsonId;

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
}
