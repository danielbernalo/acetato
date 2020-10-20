package com.nxastudios.acetato.core.domain;

public class Artist {
    private final ArtistId artistId;
    private final String name;

    private Artist(ArtistId artistId, String name) {
        this.name = name;
        this.artistId = artistId;
    }

    public static class Builder {
        private ArtistId artistId;
        private String name;

        public Builder withId(ArtistId id) {
            this.artistId = id;
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
        return artistId.get();
    }
}
