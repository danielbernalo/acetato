package com.nxastudios.acetato.delivery.http.handler;


import com.nxastudios.acetato.core.action.DeleteAlbum;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.ext.web.Router;
import io.vertx.reactivex.ext.web.RoutingContext;

public class DeleteAlbumHandler implements Handler {
    private static final String PATH = "/album/:albumId";
    private DeleteAlbum deleteAlbum;

    public DeleteAlbumHandler(DeleteAlbum deleteAlbum) {
        this.deleteAlbum = deleteAlbum;
    }

    @Override
    public void register(Router router) {
        router.delete(PATH).handler(this::handle);
    }

    private void handle(RoutingContext context) {

        String albumId = context.pathParam("albumId");
        deleteAlbum.execute(albumId).subscribe(() -> onSuccess(context), error -> onError(context, error));
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
