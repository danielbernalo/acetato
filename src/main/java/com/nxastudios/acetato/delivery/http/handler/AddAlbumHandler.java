package com.nxastudios.acetato.delivery.http.handler;

import com.nxastudios.acetato.core.action.AddAlbum;
import com.nxastudios.acetato.core.infrastructure.repositories.services.converter.AlbumDTO;
import io.vertx.core.json.Json;
import io.vertx.reactivex.ext.web.Router;
import io.vertx.reactivex.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddAlbumHandler implements Handler {
    private static final String PATH = "/album";
    private final AddAlbum addAlbum;

    private static final Logger logger = LoggerFactory.getLogger(AddAlbumHandler.class);

    public AddAlbumHandler(AddAlbum addAlbum) {

        this.addAlbum = addAlbum;
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
        logger.error(error.getLocalizedMessage(), error);
        context.response().setStatusCode(500).end(error.getLocalizedMessage());
    }

    private void onSuccess(RoutingContext context) {
        context.response().setStatusCode(201).end();
    }

}
