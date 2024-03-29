package com.nxastudios.acetato.core.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nxastudios.acetato.core.infrastructure.services.converter.AlbumDTO;
import com.nxastudios.acetato.core.infrastructure.services.converter.ArtistDTO;
import com.nxastudios.acetato.core.infrastructure.services.converter.TrackDTO;
import org.bson.codecs.pojo.annotations.BsonId;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Album {
    @BsonId
    @JsonProperty("_id")
    private final AlbumId albumId;

    @JsonProperty("title")
    private final String title;

    @JsonProperty("release_date")
    private final Long releaseDate;


    @JsonProperty("artists")
    private final List<Artist> artists;

    @JsonProperty("tracks")
    private final List<Track> tracks;

    @JsonProperty("type")
    private final AlbumType type;

    private Album(AlbumId albumId, String title, Long releaseDate, List<Artist> artists, List<Track> tracks, AlbumType type) {
        this.albumId = albumId;
        this.title = title;
        this.releaseDate = releaseDate;
        this.artists = artists;
        this.tracks = tracks;
        this.type = type;
    }

    public Album(AlbumDTO albumDTO) {

        this.albumId = albumDTO.id() != null ? new AlbumId(albumDTO.id()) : null;
        this.title = albumDTO.title();
        this.releaseDate = albumDTO.releaseDate();
        this.artists = ArtistDTO.mapArtistsFrom(albumDTO.artists());
        this.tracks = TrackDTO.mapTracksFrom(albumDTO.tracks());
        this.type = albumDTO.type();
    }

    public Album() {
        this.albumId = null;
        this.title = "";
        this.releaseDate = 0L;
        this.artists = new ArrayList();
        this.tracks = new ArrayList();
        this.type = AlbumType.ALBUM;
    }

    public static Album mapAlbumFrom(AlbumDTO album) {
        return new Album.Builder()
                .withTitle(album.title())
                .withId(album.id())
                .withType(album.type())
                .withReleaseDate(album.releaseDate())
                .build();
    }

    public String getTitle() {
        return title;
    }

    public Long getReleaseDate() {
        return releaseDate;
    }

    public String getAlbumId() {
        if (albumId == null) return "";
        return albumId.toString();
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
            return new Album(albumId, title, releaseDate, artists, tracks, type);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Album album = (Album) o;
        return Objects.equals(albumId, album.albumId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(albumId);
    }
}
