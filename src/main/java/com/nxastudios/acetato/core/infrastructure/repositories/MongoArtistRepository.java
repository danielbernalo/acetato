package com.nxastudios.acetato.core.infrastructure.repositories;

import com.nxastudios.acetato.core.domain.Artist;
import com.nxastudios.acetato.core.domain.ArtistId;
import com.nxastudios.acetato.core.domain.Artists;
import com.nxastudios.acetato.core.domain.errors.ArtistNotFound;
import com.nxastudios.acetato.core.infrastructure.repositories.services.converter.ArtistDTO;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.ext.mongo.MongoClient;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class MongoArtistRepository implements Artists {

    private static final String COLLECTION_NAME = "artists";
    private final MongoClient client;

    public MongoArtistRepository(MongoClient client) {
        this.client = client;
    }

    @Override
    public Completable put(Artist artist) {
        return Completable.fromMaybe(client.rxSave(COLLECTION_NAME, buildModelArtistFrom(artist)));
    }

    @Override
    public Completable remove(ArtistId artistId) {
        JsonObject query = new JsonObject().put("_id", artistId.toString());
        return Completable.fromMaybe(client.rxRemoveDocument(COLLECTION_NAME, query));


    }

    @Override
    public Single<Artist> getOne(ArtistId artistId) {
        JsonObject query = new JsonObject().put("_id", artistId.toString());
        return client.rxFindOne(COLLECTION_NAME, query, new JsonObject())
                .map(item -> {
                    return new Artist(ArtistDTO.buildFrom(item));
                }).toSingle()
                .onErrorResumeNext(throwable -> Single.error(new ArtistNotFound()));
    }


    @Override
    public Single<List<Artist>> list() {
        JsonObject query = new JsonObject();
        return client.rxFind(COLLECTION_NAME, query)
                .toFlowable()
                .flatMapIterable(list -> list.stream().map(json -> new Artist(ArtistDTO.buildFrom(json))).collect(Collectors.toList()))
                .toList();

    }

    private JsonObject buildModelArtistFrom(Artist artist) {
        JsonObject jsonObject = new JsonObject();
        if (artist.getArtistId() != "")
            jsonObject.put("_id", artist.getArtistId());
        jsonObject.put("name", artist.getName());

        return jsonObject;
    }
}
