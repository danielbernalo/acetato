package com.nxastudios.acetato.core.infrastructure.repositories.services.converter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nxastudios.acetato.core.domain.Album;
import com.nxastudios.acetato.core.domain.Artist;

import java.util.List;

public class TrackDTO {

    @JsonProperty("id")
    private String trackId;

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


    public TrackDTO() {
    }


    public String title() {
        return title;
    }

    public String id() {
        return trackId;
    }

    public Album album() {
        return album;
    }

    public List<Artist> artists() {
        return artists;
    }

    public Long duration() {
        return duration;
    }

    public Integer discNumber() {
        return discNumber;
    }

    public Integer trackNumber() {
        return trackNumber;
    }
}
