package com.nxastudios.acetato.core.infrastructure.repositories;

import com.nxastudios.acetato.core.domain.*;
import com.nxastudios.acetato.core.domain.errors.AlbumNotFound;
import com.nxastudios.acetato.core.infrastructure.services.converter.AlbumDTO;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.ext.mongo.MongoClient;

import java.util.List;
import java.util.stream.Collectors;

public class MongoAlbumRepository implements Albums {
    private static final String TITLE = "title";
    private static final String RELEASE_DATE = "release_date";
    private static final String ARTISTS = "artists";
    private static final String TRACKS = "tracks";
    private static final String ALBUM_TYPE = "type";

    private static final String COLLECTION_NAME = "albums";
    private MongoClient mongoClient;

    public MongoAlbumRepository(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    @Override
    public Completable put(Album album) {

        return Completable.fromMaybe(mongoClient.rxSave(COLLECTION_NAME, buildModelAlbumFrom(album)));
    }

    @Override
    public Completable remove(AlbumId albumId) {
        JsonObject query = new JsonObject().put("_id", albumId.toString());

        return Completable.fromMaybe(mongoClient.rxRemoveDocument(COLLECTION_NAME, query));
    }

    @Override
    public Single<Album> getOne(AlbumId albumId) {
        JsonObject query = new JsonObject().put("_id", albumId.toString());

        return mongoClient.rxFindOne(COLLECTION_NAME, query, new JsonObject())
                .map(item -> new Album(AlbumDTO.buildFrom(item))).toSingle()
                .onErrorResumeNext(throwable -> Single.error(new AlbumNotFound()));
    }

    @Override
    public Single<List<Album>> list() {
        JsonObject query = new JsonObject();

        return mongoClient.rxFind(COLLECTION_NAME, query)
                .toFlowable()
                .flatMapIterable(list -> list.stream().map(json -> new Album(AlbumDTO.buildFrom(json))).collect(Collectors.toList()))
                .toList();
    }

    private JsonObject buildModelAlbumFrom(Album album) {
        JsonObject jsonObject = new JsonObject()

                .put(TITLE, album.getTitle())
                .put(RELEASE_DATE, album.getReleaseDate())
                .put(ARTISTS, album.getArtists())
                .put(TRACKS, album.getTracks())
                .put(ALBUM_TYPE, album.getType());
        if (album.getAlbumId() != null)
            jsonObject.put("_id", album.getAlbumId());

        return jsonObject;
    }

}
