package com.nxastudios.acetato.delivery.http.handler;

import com.nxastudios.acetato.core.action.GetTrack;
import com.nxastudios.acetato.core.domain.errors.TrackNotFound;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.ext.web.Router;
import io.vertx.reactivex.ext.web.RoutingContext;

public class GetTrackHandler implements Handler {
    private static final String PATH = "/track/:trackId";
    private GetTrack getTrack;

    public GetTrackHandler(GetTrack getTrack) {
        this.getTrack = getTrack;
    }

    @Override
    public void register(Router router) {
        router.get(PATH).handler(this::handler);
    }

    private void handler(RoutingContext context) {
        String trackId = context.pathParam("trackId");
        getTrack.execute(trackId)
                .map(track -> JsonObject.mapFrom(track))
                .subscribe(json -> onSuccess(context, json), error -> onError(context, error));
    }

    private void onError(RoutingContext context, Throwable error) {
        String errorEncoded = new JsonObject().put("error", error.getLocalizedMessage()).encodePrettily();
        int statusCode = 500;
        if (error instanceof TrackNotFound) {
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
