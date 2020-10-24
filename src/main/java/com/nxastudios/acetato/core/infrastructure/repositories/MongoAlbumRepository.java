package com.nxastudios.acetato.core.infrastructure.repositories;

import com.nxastudios.acetato.core.domain.Album;
import com.nxastudios.acetato.core.domain.AlbumId;
import com.nxastudios.acetato.core.domain.Albums;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.ext.mongo.MongoClient;

import java.util.Map;


public class MongoAlbumRepository implements Albums {

    private static final String COLLECTION_NAME = "albums";
    private final MongoClient mongoClient;

    public MongoAlbumRepository(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    @Override
    public Completable put(Album album) {
        return Completable.fromMaybe(mongoClient.rxInsert(COLLECTION_NAME, buildModelAlbumFrom(album)));
    }

    @Override
    public Single<Album> getOne(AlbumId idAlbum) {
        return null;
    }

    @Override
    public Single<Map<AlbumId, Album>> list() {
        return null;
    }

    @Override
    public Completable remove(AlbumId idAlbum) {
        return null;
    }

    public JsonObject buildModelAlbumFrom(Album album) {
        return JsonObject.mapFrom(album);
    }

}

