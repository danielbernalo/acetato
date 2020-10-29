package com.nxastudios.acetato.delivery.http.handler;

import com.nxastudios.acetato.core.action.ListArtist;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.ext.web.Router;
import io.vertx.reactivex.ext.web.RoutingContext;

import java.util.List;
import java.util.stream.Collectors;

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
                .toFlowable()
                .flatMapIterable(i -> i.stream().map(entries -> JsonObject.mapFrom(entries)).collect(Collectors.toList()))
                .toList()
                .subscribe(items -> onSuccess(context, items), error -> onError(context, error));

    }

    private void onError(RoutingContext context, Throwable error) {
        String errorEncoded = new JsonObject().put("error", error.getLocalizedMessage()).encodePrettily();
        context.response()
                .putHeader("Content-Type", "application/json")
                .setStatusCode(500)
                .end(errorEncoded);
    }

    private void onSuccess(RoutingContext context, List<JsonObject> items) {
        context.response()
                .putHeader("Content-Type", "application/json")
                .setStatusCode(200)
                .end(items.toString());
    }
}
