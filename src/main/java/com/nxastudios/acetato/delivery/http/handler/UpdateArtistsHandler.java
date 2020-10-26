package com.nxastudios.acetato.delivery.http.handler;

import com.nxastudios.acetato.core.action.UpdateArtist;
import com.nxastudios.acetato.core.infrastructure.repositories.services.converter.ArtistDTO;
import com.nxastudios.acetato.delivery.http.handler.errors.MissingParameterException;
import io.reactivex.Single;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.ext.web.Router;
import io.vertx.reactivex.ext.web.RoutingContext;

public class UpdateArtistsHandler implements Handler {
    private static final String PATH = "/artist";
    private UpdateArtist updateArtists;

    public UpdateArtistsHandler(UpdateArtist updateArtists) {

        this.updateArtists = updateArtists;
    }

    @Override
    public void register(Router router) {
        router.put(PATH).handler(this::handle);
    }

    private void handle(RoutingContext context) {
        ArtistDTO artistDTO = Json.decodeValue(context.getBodyAsString(), ArtistDTO.class);
        Single.just(artistDTO)
                .flatMap(artist -> {
                    if (artist.id() == null){
                       return  Single.error(new MissingParameterException("_id"));
                    }
                   return Single.just(artist);
                })
                .flatMapCompletable(artist -> updateArtists.execute(artistDTO))
                .subscribe(() -> onSuccess(context), error -> onError(context, error));

    }

    private void onError(RoutingContext context, Throwable error) {
        String errorEncoded = new JsonObject().put("error", error.getLocalizedMessage()).encodePrettily();
        Integer statusCode = 500;
        if (error instanceof MissingParameterException) {
            statusCode = 400;
        }
        context.response()
                .putHeader("Content-Type", "application/json")
                .setStatusCode(statusCode)
                .end(errorEncoded);
    }

    private void onSuccess(RoutingContext context) {
        context.response()
                .putHeader("Content-Type", "application/json")
                .setStatusCode(200)
                .end();
    }

}

