package com.nxastudios.acetato.delivery.http.handler;

import com.nxastudios.acetato.core.action.UpdateAlbum;
import com.nxastudios.acetato.core.infrastructure.services.converter.AlbumDTO;
import com.nxastudios.acetato.delivery.http.handler.errors.MissingParameterException;
import io.reactivex.Single;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.ext.web.Router;
import io.vertx.reactivex.ext.web.RoutingContext;

public class UpdateAlbumHandler implements Handler {
    private static final String PATH = "/album";
    private UpdateAlbum updateAlbum;

    public UpdateAlbumHandler(UpdateAlbum updateAlbum) {

        this.updateAlbum = updateAlbum;
    }

    @Override
    public void register(Router router) {
        router.put(PATH).handler(this::handle);
    }

    private void handle(RoutingContext context) {
        AlbumDTO albumDTO = Json.decodeValue(context.getBodyAsString(), AlbumDTO.class);
        Single.just(albumDTO)
                .flatMap(album -> {
                    if (album.id() == null){
                       return  Single.error(new MissingParameterException("_id"));
                    }
                   return Single.just(album);
                })
                .flatMapCompletable(album -> updateAlbum.execute(albumDTO))
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

