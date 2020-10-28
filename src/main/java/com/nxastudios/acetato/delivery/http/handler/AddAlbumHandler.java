package com.nxastudios.acetato.delivery.http.handler;

import com.nxastudios.acetato.core.action.AddAlbum;
import com.nxastudios.acetato.core.action.AddArtist;
import com.nxastudios.acetato.core.action.AddTrack;
import com.nxastudios.acetato.core.infrastructure.services.converter.AlbumDTO;
import io.vertx.core.json.Json;
import io.vertx.reactivex.ext.web.Router;
import io.vertx.reactivex.ext.web.RoutingContext;

public class AddAlbumHandler implements Handler {
    private static final String PATH = "/album";
    private AddAlbum addAlbum;
    private AddArtist addArtist;
    private AddTrack addTrack;

    public AddAlbumHandler(AddAlbum addAlbum) {

        this.addAlbum = addAlbum;
        this.addArtist = addArtist;
        this.addTrack = addTrack;
    }

    @Override
    public void register(Router router) {
        router.post(PATH).handler(this::handle);
    }

    private void handle(RoutingContext context) {
        AlbumDTO albumDTO = Json.decodeValue(context.getBodyAsString(), AlbumDTO.class);

        addAlbum.execute(albumDTO)
                .subscribe(() -> onSuccess(context), error -> onError(context, error));
    }

    private void onError(RoutingContext context, Throwable error) {
        context.response()
                .putHeader("Content-Type", "application/json")
                .setStatusCode(500)
                .end(error.getLocalizedMessage());
    }

    private void onSuccess(RoutingContext context) {
        context.response()
                .setStatusCode(201)
                .end();
    }

}

