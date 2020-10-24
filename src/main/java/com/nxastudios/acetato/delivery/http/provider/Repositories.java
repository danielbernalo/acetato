package com.nxastudios.acetato.delivery.http.provider;

import com.nxastudios.acetato.config.Environment;
import com.nxastudios.acetato.core.domain.Albums;
import com.nxastudios.acetato.core.domain.Artists;
import com.nxastudios.acetato.core.domain.Tracks;
import com.nxastudios.acetato.core.infrastructure.repositories.MongoAlbumRepository;
import com.nxastudios.acetato.core.infrastructure.repositories.MongoArtistRepository;
import com.nxastudios.acetato.core.infrastructure.repositories.MongoTrackRepository;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.ext.mongo.MongoClient;

import static com.nxastudios.acetato.delivery.http.provider.VertxProvider.vertx;

public class Repositories {
    public static Albums albums;
    public static Artists artists;
    public static Tracks tracks;
    private static MongoClient mongoClient;



    private static JsonObject mongoOptions = new JsonObject()
            .put("connection_string", "mongodb+srv://acetato_user:kxrxdZctDlLz23CJ@cluster-serviberza.zburx.mongodb.net/acetato?retryWrites=true&w=majority");

    public static MongoClient createMongoClient() {
        MongoClient shared = MongoClient.createShared(vertx, mongoOptions);
        return shared;
    }

    private static Albums buildAlbumRepository() {
        if (Environment.REPOSITORY.equals("MONGO")) {
            mongoClient = createMongoClient();
            return new MongoAlbumRepository(mongoClient);
        }

        return null;

    }

    private static Artists buildArtistRepository() {
        if (Environment.REPOSITORY.equals("MONGO")) {
            mongoClient = createMongoClient();
            return new MongoArtistRepository(mongoClient);
        }
        return null;
    }

    private static Tracks buildTrackRepository() {
        if (Environment.REPOSITORY.equals("MONGO")) {
            mongoClient = createMongoClient();
            return new MongoTrackRepository(mongoClient);
        }

        return null;
    }

    static {
        albums = buildAlbumRepository();
        artists = buildArtistRepository();
        tracks = buildTrackRepository();
    }

}
