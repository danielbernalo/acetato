package com.nxastudios.acetato.core.domain;

import java.util.List;

public class Album {
    private final String title;
    private final Long releaseDate;
    private final List<Artist> artists;
    private final List<Track> tracks;
    private final AlbumType type;

    private Album(String title, Long releaseDate, List<Artist> artists, List<Track> tracks, AlbumType type) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.artists = artists;
        this.tracks = tracks;
        this.type = type;
    }

    public static class Builder {
        private String title;
        private Long releaseDate;
        private List<Artist> artists;
        private List<Track> tracks;
        private AlbumType type;

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

        public Album builder() {
            return new Album(title, releaseDate, artists, tracks, type);
        }
    }

}
