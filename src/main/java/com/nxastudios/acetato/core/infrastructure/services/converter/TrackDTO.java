package com.nxastudios.acetato.core.infrastructure.services.converter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nxastudios.acetato.core.domain.Album;
import com.nxastudios.acetato.core.domain.Artist;
import com.nxastudios.acetato.core.domain.Track;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.bson.codecs.pojo.annotations.BsonId;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TrackDTO implements Serializable {

    private static final String TRACK_ID = "_id";
    private static final String TITLE = "title";
    private static final String ALBUM = "album";
    private static final String ARTISTS = "artists";
    private static final String DURATION = "duration";
    private static final String DISC_NUMBER = "disc_number";
    private static final String TRACK_NUMBER = "track_number";


    @BsonId
    @JsonProperty(TRACK_ID)
    private String trackId;

    @JsonProperty(TITLE)
    private String title;

    @JsonProperty(ALBUM)
    private AlbumDTO album;

    @JsonProperty(ARTISTS)
    private List<ArtistDTO> artists;

    @JsonProperty(DURATION)
    private Long duration;

    @JsonProperty(DISC_NUMBER)
    private Integer discNumber;

    @JsonProperty(TRACK_NUMBER)
    private Integer trackNumber;

    @JsonCreator
    public TrackDTO(@BsonId @JsonProperty(TRACK_ID) String trackId,
                    @JsonProperty(TITLE) String title,
                    @JsonProperty(ALBUM) AlbumDTO album,
                    @JsonProperty(ARTISTS) List<ArtistDTO> artists,
                    @JsonProperty(DURATION) Long duration,
                    @JsonProperty(DISC_NUMBER) Integer discNumber,
                    @JsonProperty(TRACK_NUMBER) Integer trackNumber) {

        this.trackId = trackId;
        this.title = title;
        this.album = album;
        this.artists = artists;
        this.duration = duration;
        this.discNumber = discNumber;
        this.trackNumber = trackNumber;
    }

    public static List<TrackDTO> buildFrom(List<Track> tracks) {
        return tracks.stream()
                .map(track -> TrackDTO.buildFrom(track))
                .collect(Collectors.toList());

    }

    public static List<TrackDTO> buildFrom(JsonArray tracks) {
        return tracks
                .stream()
                .map(i -> TrackDTO.buildFrom(((JsonObject) i)))
                .collect(Collectors.toList());
    }

    public static TrackDTO buildFrom(JsonObject json) {
        AlbumDTO albumDTO = json.getJsonObject(ALBUM) != null ? AlbumDTO.buildFrom(json.getJsonObject(ALBUM)) : new AlbumDTO();
        List<ArtistDTO> artistDTOS = json.getJsonObject(ALBUM) != null ? ArtistDTO.buildFrom(json.getJsonArray(ARTISTS)) : new ArrayList();

        return new TrackDTO(
                json.getString(TRACK_ID, ""),
                json.getString(TITLE, ""),
                albumDTO,
                artistDTOS,
                json.getLong(DURATION, 0L),
                json.getInteger(DISC_NUMBER, 0),
                json.getInteger(TRACK_NUMBER, 0)
        );
    }

    public static List<Track> mapTracksFrom(List<TrackDTO> tracks) {
        if (tracks == null) return new ArrayList();
        return tracks.stream()
                .map(trackDTO -> new Track(trackDTO))
                .collect(Collectors.toList());
    }

    private static TrackDTO buildFrom(Track track) {
        Album album = track.getAlbum() != null ? track.getAlbum() : new Album();
        List<Artist> artists = track.getArtists() != null ? track.getArtists() : new ArrayList();

        return new TrackDTO(
                track.getTrackId(),
                track.getTitle(),
                AlbumDTO.buildFrom(album),
                ArtistDTO.buildFrom(artists),
                track.getDuration(),
                track.getDiscNumber(),
                track.getTrackNumber()
        );
    }

    public String getTrackId() {
        return trackId;
    }

    public String getTitle() {
        return title;
    }

    public AlbumDTO getAlbum() {
        return album;
    }

    public List<ArtistDTO> getArtists() {
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
}
