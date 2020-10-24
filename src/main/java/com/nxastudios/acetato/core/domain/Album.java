package com.nxastudios.acetato.core.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Album {

    @JsonProperty("album_id")
    private final AlbumId albumId;

    @JsonProperty("title")
    private final String title;

    @JsonProperty("release_date")
    private final Long releaseDate;

    @JsonProperty("artists")
    private final List<Artist> artists;

    @JsonProperty("tracks")
    private final List<Track> tracks;

    @JsonProperty("album_type")
    private final AlbumType type;

    private Album(AlbumId albumId, String title, Long releaseDate, List<Artist> artists, List<Track> tracks, AlbumType type) {
        this.albumId = albumId;
        this.title = title;
        this.releaseDate = releaseDate;
        this.artists = artists;
        this.tracks = tracks;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public Long getReleaseDate() {
        return releaseDate;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public AlbumType getType() {
        return type;
    }


    public static class Builder {
        private AlbumId albumId;
        private String title;
        private Long releaseDate;
        private List<Artist> artists;
        private List<Track> tracks;
        private AlbumType type;

        public Builder withId(String albumId) {
            this.albumId = new AlbumId(albumId);
            return this;
        }

        public Builder withTitle(String title) {
            if (title != null)
                this.title = title;
            return this;
        }

        public Builder withReleaseDate(Long releaseDate) {
            if (releaseDate != null)
            this.releaseDate = releaseDate;
            return this;
        }

        public Builder withArtists(List<Artist> artists) {
            if (artists != null)
            this.artists = artists;
            return this;
        }

        public Builder withTracks(List<Track> tracks) {
            if (tracks != null)
            this.tracks = tracks;
            return this;
        }

        public Builder withType(AlbumType type) {
            if (type != null)
            this.type = type;
            return this;
        }

        public Album build() {
            return new Album(albumId, title, releaseDate, artists, tracks, type);
        }
    }

    public String getAlbumId() {
        return albumId.get();
    }

}
