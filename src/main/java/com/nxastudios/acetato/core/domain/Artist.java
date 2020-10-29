package com.nxastudios.acetato.core.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nxastudios.acetato.core.infrastructure.services.converter.ArtistDTO;

import java.util.Objects;


public class Artist {

    @JsonProperty("_id")
    private final ArtistId artistId;

    @JsonProperty("name")
    private final String name;

    private Artist(ArtistId artistId, String name) {
        this.name = name;
        this.artistId = artistId;
    }

    public Artist(ArtistDTO artistDTO) {
        this.name = artistDTO.name();
        this.artistId = artistDTO.id() != "" ? new ArtistId(artistDTO.id()) : null;
    }


    public String getName() {
        return name;
    }

    public String getArtistId() {
        return artistId.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artist artist = (Artist) o;
        return Objects.equals(artistId, artist.artistId) &&
                Objects.equals(name, artist.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(artistId, name);
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
}
