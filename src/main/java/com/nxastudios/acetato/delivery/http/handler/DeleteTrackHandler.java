package com.nxastudios.acetato.delivery.http.handler;


import com.nxastudios.acetato.core.action.DeleteTrack;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.ext.web.Router;
import io.vertx.reactivex.ext.web.RoutingContext;

public class DeleteTrackHandler implements Handler {
    private static final String PATH = "/track/:trackId";
    private DeleteTrack deleteTrack;

    public DeleteTrackHandler(DeleteTrack deleteTrack) {
        this.deleteTrack = deleteTrack;
    }

    @Override
    public void register(Router router) {
        router.delete(PATH).handler(this::handle);
    }

    private void handle(RoutingContext context) {

        String trackId = context.pathParam("trackId");
        deleteTrack.execute(trackId).subscribe(() -> onSuccess(context), error -> onError(context, error));
    }

    private void onError(RoutingContext context, Throwable error) {
        String errorEncoded = new JsonObject().put("error", error.getLocalizedMessage()).encodePrettily();
        context.response()
                .putHeader("Content-Type", "application/json")
                .setStatusCode(500)
                .end(errorEncoded);
    }

    private void onSuccess(RoutingContext context) {
        context.response().setStatusCode(204).end();
    }

}
