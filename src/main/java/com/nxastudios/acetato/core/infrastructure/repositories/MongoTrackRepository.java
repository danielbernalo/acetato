package com.nxastudios.acetato.core.infrastructure.repositories;

import com.nxastudios.acetato.core.domain.Track;
import com.nxastudios.acetato.core.domain.TrackId;
import com.nxastudios.acetato.core.domain.Tracks;
import com.nxastudios.acetato.core.domain.errors.TrackNotFound;
import com.nxastudios.acetato.core.infrastructure.services.converter.TrackDTO;
import io.reactivex.Completable;
import io.reactivex.Single;
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

    private JsonObject buildModelTrackFrom(Track track) {
        JsonObject jsonObject = new JsonObject()

                .put(TRACK_TITLE, track.getTitle())
                .put(TRACK_ALBUM, track.getAlbum())
                .put(TRACK_ARTISTS, track.getArtists())
                .put(TRACK_DURATION, track.getDuration())
                .put(TRACK_DISC_NUMBER, track.getDiscNumber())
                .put(TRACK_NUMBER, track.getTrackNumber());

        if (track.getTrackId() != null)
            jsonObject.put(TRACK_ID, track.getTrackId());

        return jsonObject;
    }
}
