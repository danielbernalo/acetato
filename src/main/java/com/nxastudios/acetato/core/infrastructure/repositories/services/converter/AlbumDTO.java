package com.nxastudios.acetato.core.infrastructure.repositories.services.converter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nxastudios.acetato.core.domain.AlbumType;
import com.nxastudios.acetato.core.domain.Artist;
import com.nxastudios.acetato.core.domain.Track;

import java.util.List;

public class AlbumDTO {
    @JsonProperty("id")
    private String albumId;

    @JsonProperty("title")
    private String title;

    @JsonProperty("release_date")
    private Long releaseDate;

    @JsonProperty("artists")
    private List<Artist> artists;

    @JsonProperty("tracks")
    private List<Track> tracks;

    @JsonProperty("type")
    private AlbumType type;

    public String title() {
        return title;
    }

    public Long releaseDate() {
        return releaseDate;
    }

    public List<Artist> artists() {
        return artists;
    }

    public List<Track> tracks() {
        return tracks;
    }

    public AlbumType type() {
        return type;
    }

    public String albumId() {
        return albumId;
    }
}
