package com.nxastudios.acetato.delivery.http.handler;

import com.nxastudios.acetato.core.action.AddArtist;
import com.nxastudios.acetato.core.infrastructure.repositories.services.converter.ArtistDTO;
import io.vertx.core.json.Json;
import io.vertx.reactivex.ext.web.Router;
import io.vertx.reactivex.ext.web.RoutingContext;

public class AddArtistsHandler implements Handler {
    private static final String PATH = "/artist";
    private AddArtist addArtists;

    public AddArtistsHandler(AddArtist addArtists) {

        this.addArtists = addArtists;
    }

    @Override
    public void register(Router router) {
        router.post(PATH).handler(this::handle);
    }

    private void handle(RoutingContext context) {
        ArtistDTO artistDTO = Json.decodeValue(context.getBodyAsString(), ArtistDTO.class);

        addArtists.execute(artistDTO)
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

