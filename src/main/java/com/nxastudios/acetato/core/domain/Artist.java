package com.nxastudios.acetato.core.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nxastudios.acetato.core.infrastructure.repositories.services.converter.ArtistDTO;


public class Artist {

    @JsonProperty("artist_id")
    private final ArtistId artistId;

    @JsonProperty("name")
    private final String name;

    private Artist(ArtistId artistId, String name) {
        this.name = name;
        this.artistId = artistId;
    }

    public String getName() {
        return name;
    }

    public static class Builder {
        private ArtistId artistId;
        private String name;

        public Builder withId(String id) {
            this.artistId = new ArtistId(id);
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Artist build() {
            return new Artist(artistId, name);
        }
    }

    public String getArtistId() {
        return artistId.toString();
    }

    public Artist(ArtistDTO artistDTO) {
        this.name = artistDTO.name();
        this.artistId = artistDTO.id() != "" ? new ArtistId(artistDTO.id()) : null;
    }
}
