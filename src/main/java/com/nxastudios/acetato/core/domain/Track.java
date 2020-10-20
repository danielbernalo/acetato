package com.nxastudios.acetato.core.domain;

import java.util.List;

public class Track {
    private final TrackId trackId;
    private final String title;
    private final Album album;
    private final List<Artist> artists;
    private final Long duration;
    private final Integer discNumber;
    private final Integer trackNumber;


    private Track(TrackId trackId, String title, Album album, List<Artist> artists, Long duration, Integer discNumber, Integer trackNumber) {
        this.trackId = trackId;
        this.title = title;
        this.album = album;
        this.artists = artists;
        this.discNumber = discNumber;
        this.duration = duration;
        this.trackNumber = trackNumber;
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

    public String getTrackId() {
        return trackId.get();
    }
}
