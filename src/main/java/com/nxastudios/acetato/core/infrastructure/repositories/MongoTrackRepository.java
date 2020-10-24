package com.nxastudios.acetato.core.infrastructure.repositories;

import com.nxastudios.acetato.core.domain.Track;
import com.nxastudios.acetato.core.domain.TrackId;
import com.nxastudios.acetato.core.domain.Tracks;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.ext.mongo.MongoClient;

import java.util.Map;

public class MongoTrackRepository implements Tracks {

    private static final String COLLECTION_NAME = "tracks";
    private final MongoClient mongoClient;

    public MongoTrackRepository(MongoClient mongoClient) {

        this.mongoClient = mongoClient;
    }

    @Override
    public Completable put(Track track) {
        return Completable.fromMaybe(mongoClient.rxInsert(COLLECTION_NAME, buildModelTrackFrom(track)));
    }

    @Override
    public Completable remove(TrackId trackId) {
        return null;
    }

    @Override
    public Single<Track> getOne(TrackId trackId) {
        return null;
    }

    @Override
    public Single<Map<TrackId, Track>> list() {
        return null;
    }

    private JsonObject buildModelTrackFrom(Track track) {
        return JsonObject.mapFrom(track);
    }
}

