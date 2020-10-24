package com.nxastudios.acetato.core.infrastructure.repositories.services.converter;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ArtistDTO {

    @JsonProperty("name")
    private String name;

    @JsonAlias({"id", "_id"})

    private String artistId;

    public ArtistDTO() {
    }

    public ArtistDTO(String artistId, String name) {
    }

    public String name() {
        return name;
    }

    public String artistId() {
        return artistId;
    }
}
