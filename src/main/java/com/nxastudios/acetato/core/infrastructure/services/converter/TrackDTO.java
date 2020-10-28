package com.nxastudios.acetato.core.infrastructure.services.converter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nxastudios.acetato.core.domain.Track;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.bson.codecs.pojo.annotations.BsonId;

import java.util.List;
import java.util.stream.Collectors;

public class TrackDTO {
    @BsonId
    @JsonProperty("_id")
    private String trackId;

    @JsonProperty("title")
    private String title;

    @JsonProperty("album")
    private AlbumDTO album;

    @JsonProperty("artists")
    private List<ArtistDTO> artists;

    @JsonProperty("duration")
    private Long duration;

    @JsonProperty("disc_number")
    private Integer discNumber;

    @JsonProperty("track_number")
    private Integer trackNumber;

    @JsonCreator
    public TrackDTO(@BsonId @JsonProperty("_id") String trackId,
                    @JsonProperty("title") String title,
                    @JsonProperty("album") AlbumDTO album,
                    @JsonProperty("") List<ArtistDTO> artists,
                    @JsonProperty("name") Long duration,
                    @JsonProperty("name") Integer discNumber,
                    @JsonProperty("name") Integer trackNumber) {

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
        return new TrackDTO(
                json.getString("_id"),
                json.getString("title"),
                AlbumDTO.buildFrom(json.getJsonObject("album")),
                ArtistDTO.buildFrom(json.getJsonArray("artists")),
                json.getLong("duration"),
                json.getInteger("disc_number"),
                json.getInteger("track_number")
        );
    }

    public static List<Track> mapTracksFrom(List<TrackDTO> tracks) {
        return tracks.stream()
                .map(trackDTO -> new Track(trackDTO))
                .collect(Collectors.toList());
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

    private static TrackDTO buildFrom(Track track) {
        return new TrackDTO(
                track.getTrackId(),
                track.getTitle(),
                AlbumDTO.buildFrom(track.getAlbum()),
                ArtistDTO.buildFrom(track.getArtists()),
                track.getDuration(),
                track.getDiscNumber(),
                track.getTrackNumber()
        );
    }
}
