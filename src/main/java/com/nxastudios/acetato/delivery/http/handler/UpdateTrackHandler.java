package com.nxastudios.acetato.delivery.http.handler;

import com.nxastudios.acetato.core.action.UpdateTrack;
import com.nxastudios.acetato.core.infrastructure.services.converter.TrackDTO;
import com.nxastudios.acetato.delivery.http.handler.errors.MissingParameterException;
import io.reactivex.Single;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.ext.web.Router;
import io.vertx.reactivex.ext.web.RoutingContext;

public class UpdateTrackHandler implements Handler {
    private static final String PATH = "/track";
    private UpdateTrack updateTrack;

    public UpdateTrackHandler(UpdateTrack updateTrack) {

        this.updateTrack = updateTrack;
    }

    @Override
    public void register(Router router) {
        router.put(PATH).handler(this::handle);
    }

    private void handle(RoutingContext context) {
        TrackDTO trackDTO = Json.decodeValue(context.getBodyAsString(), TrackDTO.class);
        Single.just(trackDTO)
                .flatMap(track -> {
                    if (track.getTrackId() == null) {
                        return Single.error(new MissingParameterException("_id"));
                    }
                    return Single.just(track);
                })
                .flatMapCompletable(track -> updateTrack.execute(trackDTO))
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

