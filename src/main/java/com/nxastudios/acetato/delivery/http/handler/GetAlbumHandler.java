package com.nxastudios.acetato.delivery.http.handler;

import com.nxastudios.acetato.core.action.GetAlbum;
import com.nxastudios.acetato.core.domain.errors.AlbumNotFound;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.ext.web.Router;
import io.vertx.reactivex.ext.web.RoutingContext;

public class GetAlbumHandler implements Handler {
    private static final String PATH = "/album/:albumId";
    private GetAlbum getAlbum;

    public GetAlbumHandler(GetAlbum getAlbum) {
        this.getAlbum = getAlbum;
    }

    @Override
    public void register(Router router) {
        router.get(PATH).handler(this::handler);
    }

    private void handler(RoutingContext context) {
        String albumId = context.pathParam("albumId");
        getAlbum.execute(albumId)
                .map(album -> JsonObject.mapFrom(album))
                .subscribe(json -> onSuccess(context, json), error -> onError(context, error));
    }

    private void onError(RoutingContext context, Throwable error) {
        String errorEncoded = new JsonObject().put("error", error.getLocalizedMessage()).encodePrettily();
        int statusCode = 500;
        if (error instanceof AlbumNotFound) {
            statusCode = 404;
        }
        context.response()
                .putHeader("Content-Type", "application/json")
                .setStatusCode(statusCode)
                .end(errorEncoded);

    }


    private void onSuccess(RoutingContext context, JsonObject data) {
        context.response()
                .putHeader("Content-Type", "application/json")
                .setStatusCode(200)
                .end(data.encodePrettily());
    }
}
