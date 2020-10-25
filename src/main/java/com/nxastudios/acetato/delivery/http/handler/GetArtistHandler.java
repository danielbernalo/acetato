package com.nxastudios.acetato.delivery.http.handler;

import com.nxastudios.acetato.core.action.GetArtist;
import com.nxastudios.acetato.core.infrastructure.repositories.services.converter.ArtistDTO;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.ext.web.Router;
import io.vertx.reactivex.ext.web.RoutingContext;

public class GetArtistHandler implements Handler {
    private static final String PATH = "/artist/:artistId";
    private GetArtist getArtist;

    public GetArtistHandler(GetArtist getArtist){
        this.getArtist = getArtist;
    }
    @Override
    public void register(Router router) {
        router.get(PATH).handler(this::handler);
    }

    private void handler(RoutingContext context) {
        String artistId = context.pathParam("artistId");
        getArtist.execute(artistId)

                .map(artist -> JsonObject.mapFrom(ArtistDTO.buildFrom(artist)))
                .subscribe(json -> onSuccess(context, json), error -> onError(context, error ));
    }

    private void onError(RoutingContext context, Throwable error) {
        error.printStackTrace();
        context.response().setStatusCode(500).end(error.getLocalizedMessage());
    }

    private void onSuccess(RoutingContext context, JsonObject data) {
        context.response().setStatusCode(200).end(data.encode());
    }
}
