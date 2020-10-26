package com.nxastudios.acetato.delivery.http.handler;

import com.nxastudios.acetato.core.action.GetArtist;
import com.nxastudios.acetato.core.domain.errors.ArtistNotFound;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.ext.web.Router;
import io.vertx.reactivex.ext.web.RoutingContext;

public class GetArtistHandler implements Handler {
    private static final String PATH = "/artist/:artistId";
    private GetArtist getArtist;

    public GetArtistHandler(GetArtist getArtist) {
        this.getArtist = getArtist;
    }

    @Override
    public void register(Router router) {
        router.get(PATH).handler(this::handler);
    }

    private void handler(RoutingContext context) {
        String artistId = context.pathParam("artistId");
        getArtist.execute(artistId)
                .map(artist -> JsonObject.mapFrom(artist))
                .subscribe(json -> onSuccess(context, json), error -> onError(context, error));
    }

    private void onError(RoutingContext context, Throwable error) {
        String errorEncoded = new JsonObject().put("error", error.getLocalizedMessage()).encodePrettily();
        if (error instanceof ArtistNotFound) {
            context.response()
                    .putHeader("Content-Type", "application/json")
                    .setStatusCode(404)
                    .end(errorEncoded);
        } else {
            context.response()
                    .putHeader("Content-Type", "application/json")
                    .setStatusCode(500)
                    .end(errorEncoded);
        }
    }


    private void onSuccess(RoutingContext context, JsonObject data) {
        context.response()
                .putHeader("Content-Type", "application/json")
                .setStatusCode(200)
                .end(data.encodePrettily());
    }
}
