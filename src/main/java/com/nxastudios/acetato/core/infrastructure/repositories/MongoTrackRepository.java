package com.nxastudios.acetato.core.infrastructure.repositories;

import com.nxastudios.acetato.core.domain.*;
import com.nxastudios.acetato.core.domain.errors.TrackNotFound;
import com.nxastudios.acetato.core.infrastructure.services.converter.TrackDTO;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.ext.mongo.MongoClient;

import java.util.List;
import java.util.stream.Collectors;

import static com.nxastudios.acetato.core.infrastructure.repositories.Constants.*;

public class MongoTrackRepository implements Tracks {

    private static final String COLLECTION_NAME = "tracks";
    private MongoClient mongoClient;

    public MongoTrackRepository(MongoClient mongoClient) {

        this.mongoClient = mongoClient;
    }

    @Override
    public Completable put(Track track) {

        return Completable.fromMaybe(mongoClient.rxSave(COLLECTION_NAME, buildModelTrackFrom(track)));
    }


    @Override
    public Completable remove(TrackId trackId) {
        JsonObject query = new JsonObject().put(TRACK_ID, trackId.toString());

        return Completable.fromMaybe(mongoClient.rxRemoveDocument(COLLECTION_NAME, query));
    }

    @Override
    public Single<Track> getOne(TrackId trackId) {
        JsonObject query = new JsonObject().put(TRACK_ID, trackId.toString());

        return mongoClient.rxFindOne(COLLECTION_NAME, query, new JsonObject())
                .map(item -> new Track(TrackDTO.buildFrom(item))).toSingle()
                .onErrorResumeNext(throwable -> Single.error(new TrackNotFound()));

    }

    @Override
    public Single<List<Track>> list() {
        JsonObject query = new JsonObject();

        return mongoClient.rxFind(COLLECTION_NAME, query)
                .toFlowable()
                .flatMapIterable(list -> list.stream().map(json -> new Track(TrackDTO.buildFrom(json)))
                        .collect(Collectors.toList()))
                .toList();
    }
    private JsonObject buildModelAlbumFrom(Album album) {
        JsonObject jsonObject = new JsonObject()

                .put(ALBUM_TITLE, album.getTitle())
                .put(ALBUM_RELEASE_DATE, album.getReleaseDate())
                .put(ALBUM_ALBUM_TYPE, album.getType());
        if (album.getAlbumId() != "")
            jsonObject.put(ALBUM_ID, album.getAlbumId());

        return jsonObject;
    }

    private JsonArray buildModelArtistFrom(List<Artist> artists) {
        JsonArray jsonArtist = new JsonArray();
        artists.forEach(artist -> jsonArtist.add(
                new JsonObject()
                        .put("name", artist.getName())
                        .put(ARTIST_ID, artist.getArtistId())
        ));
        return jsonArtist;
    }

    private JsonObject buildModelTrackFrom(Track track) {
        JsonObject jsonObject = new JsonObject()

                .put(TRACK_TITLE, track.getTitle())
                .put(TRACK_ALBUM, buildModelAlbumFrom(track.getAlbum()))
                .put(TRACK_ARTISTS, buildModelArtistFrom(track.getArtists()))
                .put(TRACK_DURATION, track.getDuration())
                .put(TRACK_DISC_NUMBER, track.getDiscNumber())
                .put(TRACK_NUMBER, track.getTrackNumber());

        if (track.getTrackId() != "")
            jsonObject.put(TRACK_ID, track.getTrackId());

        return jsonObject;
    }
}
