package com.nxastudios.acetato.delivery.http.handler;

import com.nxastudios.acetato.core.action.ListArtist;
import com.nxastudios.acetato.core.domain.Artist;
import com.nxastudios.acetato.core.domain.ArtistId;
import com.nxastudios.acetato.core.infrastructure.repositories.services.converter.ArtistDTO;
import io.vertx.core.json.Json;
import io.vertx.reactivex.ext.web.Router;
import io.vertx.reactivex.ext.web.RoutingContext;

import java.util.Map;

public class ListArtistHandler implements Handler {
    private static final String PATH = "/artists";
    private ListArtist listArtist;

    public ListArtistHandler(ListArtist listArtist) {
        this.listArtist = listArtist;
    }

    @Override
    public void register(Router router) {
        router.get(PATH).handler(this::handle);
    }

    private void handle(RoutingContext context) {
        listArtist.execute()
                .subscribe(items -> onSuccess(context, items), error -> onError(context, error));

    }

    private void onError(RoutingContext context, Throwable error) {
        error.printStackTrace();
        context.response().setStatusCode(500).end(error.getLocalizedMessage());
    }

    private void onSuccess(RoutingContext context, Map<String, ArtistDTO> items) {
        context.response().setStatusCode(200).end(Json.encode(items));
    }
}
