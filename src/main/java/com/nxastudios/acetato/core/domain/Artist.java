package com.nxastudios.acetato.core.domain;

public class Artist {
    private final String idArtist;
    private final String name;

    private Artist(String idArtist, String name) {
        this.name = name;
        this.idArtist = idArtist;
    }

    public static class Builder {
        private String idArtist;
        private String name;

        public Builder withId(String id) {
            this.idArtist = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Artist build() {
            return new Artist(idArtist, name);
        }
    }

    public String getIdArtist() {
        return idArtist;
    }
}
