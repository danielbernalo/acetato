package com.nxastudios.acetato.core.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nxastudios.acetato.core.infrastructure.services.converter.ArtistDTO;
import com.nxastudios.acetato.core.infrastructure.services.converter.TrackDTO;

import java.util.List;

public class Track {

    @JsonProperty("_id")
    private TrackId trackId;

    @JsonProperty("title")
    private String title;

    @JsonProperty("album")
    private Album album;

    @JsonProperty("artists")
    private List<Artist> artists;

    @JsonProperty("duration")
    private Long duration;

    @JsonProperty("disc_number")
    private Integer discNumber;

    @JsonProperty("track_number")
    private Integer trackNumber;


    public Track(TrackId trackId, String title, Album album, List<Artist> artists, Long duration, Integer discNumber, Integer trackNumber) {
        this.trackId = trackId;
        this.title = title;
        this.album = album;
        this.artists = artists;
        this.discNumber = discNumber;
        this.duration = duration;
        this.trackNumber = trackNumber;
    }

    public Track(TrackDTO trackDTO) {
        this.trackId = new TrackId(trackDTO.getTrackId());
        this.title = trackDTO.getTitle();
        this.album = Album.mapAlbumFrom(trackDTO.getAlbum());
        this.artists = ArtistDTO.mapArtistsFrom(trackDTO.getArtists());
        this.discNumber = trackDTO.getDiscNumber();
        this.duration = trackDTO.getDuration();
        this.trackNumber = trackDTO.getTrackNumber();
    }


    public String getTrackId() {
        return trackId.toString();
    }

    public String getTitle() {
        return title;
    }

    public Album getAlbum() {
        return album;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public Long getDuration() {
        return duration;
    }

    public Integer getDiscNumber() {
        return discNumber;
    }

    public Integer getTrackNumber() {
        return trackNumber;
    }


    public static class Builder {
        private TrackId trackId;
        private String title;
        private Album album;
        private List<Artist> artists;
        private Long duration;
        private Integer discNumber;
        private Integer trackNumber;

        public Builder withIdTrack(TrackId trackId) {
            this.trackId = trackId;
            return this;
        }

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withAlbum(Album album) {
            this.album = album;
            return this;
        }

        public Builder withArtists(List<Artist> artists) {
            this.artists = artists;
            return this;
        }

        public Builder withDuration(Long duration) {
            this.duration = duration;
            return this;
        }

        public Builder WithDiscNumber(Integer discNumber) {
            this.discNumber = discNumber;
            return this;
        }

        public Builder withTrackNumber(Integer trackNumber) {
            this.trackNumber = trackNumber;
            return this;
        }

        public Track build() {
            return new Track(trackId, title, album, artists, duration, discNumber, trackNumber);
        }
    }

}
