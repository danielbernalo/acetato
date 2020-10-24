package com.nxastudios.acetato.core.infrastructure.repositories;

import com.nxastudios.acetato.core.domain.Artist;
import com.nxastudios.acetato.core.domain.ArtistId;
import com.nxastudios.acetato.core.domain.Artists;
import com.nxastudios.acetato.core.infrastructure.repositories.services.converter.ArtistDTO;
import com.nxastudios.acetato.delivery.http.provider.Repositories;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.ext.mongo.MongoClient;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MongoArtistRepository implements Artists {

    private static final String COLLECTION_NAME = "artists";
    private final MongoClient client;

    public MongoArtistRepository(MongoClient client) {
        this.client = client;
    }

    @Override
    public Completable put(Artist artist) {
        return Completable.fromMaybe(client.rxInsert(COLLECTION_NAME, buildModelArtistFrom(artist)));
    }

    @Override
    public Completable remove(ArtistId artistId) {
        return null;
    }

    @Override
    public Single<Artist> getOne(ArtistId artistId) {
        return null;
    }

    @Override
    public Single<Map<ArtistId, Artist>> list() {

        JsonObject query = new JsonObject();
//        return client.rxFind(COLLECTION_NAME, query);
        return null;
    }

    private JsonObject buildModelArtistFrom(Artist artist) {
        return JsonObject.mapFrom(artist);
    }
}


class Main {
    public static void main(String[] args) {
        MongoClient client = Repositories.createMongoClient();
        Map<String, Artist> getArtists = new HashMap();

        client.rxFind("artists", new JsonObject())
                .map(items -> Arrays.stream(items.toArray())
                        .map(item -> getArtists.put(item.getString("_id"), new Artist.Builder().withName(item.getString("name")).build())))
                .subscribe(items -> {
                    System.out.println(getArtists);
                });
    }
}
