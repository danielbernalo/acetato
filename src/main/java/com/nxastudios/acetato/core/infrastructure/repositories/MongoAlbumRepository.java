package com.nxastudios.acetato.core.infrastructure.repositories;

import com.nxastudios.acetato.core.domain.*;
import com.nxastudios.acetato.core.domain.errors.AlbumNotFound;
import com.nxastudios.acetato.core.infrastructure.services.converter.AlbumDTO;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.ext.mongo.MongoClient;

import java.util.List;
import java.util.stream.Collectors;

import static com.nxastudios.acetato.core.infrastructure.repositories.Constants.*;

public class MongoAlbumRepository implements Albums {


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
        JsonObject query = new JsonObject().put(ALBUM_ID, albumId.toString());

        return Completable.fromMaybe(mongoClient.rxRemoveDocument(COLLECTION_NAME, query));
    }

    @Override
    public Single<Album> getOne(AlbumId albumId) {
        JsonObject query = new JsonObject().put(ALBUM_ID, albumId.toString());

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

                .put(ALBUM_TITLE, album.getTitle())
                .put(ALBUM_RELEASE_DATE, album.getReleaseDate())
                .put(ALBUM_ARTISTS, buildModelArtistFrom(album.getArtists()))
                .put(ALBUM_TRACKS, buildModelTrackFrom(album.getTracks()))
                .put(ALBUM_ALBUM_TYPE, album.getType());
        if (album.getAlbumId() != "")
            jsonObject.put(ALBUM_ID, album.getAlbumId());

        return jsonObject;
    }


    private JsonArray buildModelTrackFrom(List<Track> tracks) {
        JsonArray jsonArtist = new JsonArray();
        tracks.forEach(track -> jsonArtist.add(
                new JsonObject()
                        .put(TRACK_TITLE, track.getTitle())
                        .put(TRACK_DURATION, track.getDuration())
                        .put(TRACK_DISC_NUMBER, track.getDiscNumber())
                        .put(TRACK_NUMBER, track.getTrackNumber())
        ));
        return jsonArtist;

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

}
