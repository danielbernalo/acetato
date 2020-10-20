package com.nxastudios.acetato.core.domain;

import java.util.List;

public class Album {
    private final AlbumId idAlbum;
    private final String title;
    private final Long releaseDate;
    private final List<Artist> artists;
    private final List<Track> tracks;
    private final AlbumType type;

    private Album(AlbumId idAlbum, String title, Long releaseDate, List<Artist> artists, List<Track> tracks, AlbumType type) {
        this.idAlbum = idAlbum;
        this.title = title;
        this.releaseDate = releaseDate;
        this.artists = artists;
        this.tracks = tracks;
        this.type = type;
    }

    public static class Builder {
        private AlbumId idAlbum;
        private String title;
        private Long releaseDate;
        private List<Artist> artists;
        private List<Track> tracks;
        private AlbumType type;

        public Builder withId(AlbumId idAlbum) {
            this.idAlbum = idAlbum;
            return this;
        }

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withReleaseDate(Long releaseDate) {
            this.releaseDate = releaseDate;
            return this;
        }

        public Builder withArtists(List<Artist> artists) {
            this.artists = artists;
            return this;
        }

        public Builder withTracks(List<Track> tracks) {
            this.tracks = tracks;
            return this;
        }

        public Builder withType(AlbumType type) {
            this.type = type;
            return this;
        }

        public Album build() {
            return new Album(idAlbum, title, releaseDate, artists, tracks, type);
        }
    }

    public String getIdAlbum() {
        return idAlbum.get();
    }

}
