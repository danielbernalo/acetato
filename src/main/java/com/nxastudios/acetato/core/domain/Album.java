package com.nxastudios.acetato.core.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nxastudios.acetato.core.infrastructure.services.converter.AlbumDTO;
import com.nxastudios.acetato.core.infrastructure.services.converter.ArtistDTO;
import com.nxastudios.acetato.core.infrastructure.services.converter.TrackDTO;
import org.bson.codecs.pojo.annotations.BsonId;

import java.util.ArrayList;
import java.util.List;

public class Album {
    @BsonId
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

        this.albumId = new AlbumId(albumDTO.id());
        this.title = albumDTO.title();
        this.releaseDate = albumDTO.releaseDate();
        this.artists = ArtistDTO.mapArtistsFrom(albumDTO.artists());
        this.tracks = TrackDTO.mapTracksFrom(albumDTO.tracks());
        this.type = albumDTO.type();
        System.out.println("Album transformed");
    }

    public Album() {
        this.albumId = new AlbumId("");
        this.title = "";
        this.releaseDate = 0L;
        this.artists = new ArrayList();
        this.tracks = new ArrayList();
        this.type = AlbumType.valueOf(null);
    }

    public String getTitle() {
        return title;
    }

    public Long getReleaseDate() {
        return releaseDate;
    }

    public String getAlbumId() {
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


}
