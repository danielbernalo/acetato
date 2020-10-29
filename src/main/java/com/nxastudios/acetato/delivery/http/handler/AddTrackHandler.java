package com.nxastudios.acetato.delivery.http.handler;

import com.nxastudios.acetato.core.action.AddTrack;
import com.nxastudios.acetato.core.infrastructure.services.converter.TrackDTO;
import io.vertx.reactivex.ext.web.Router;
import io.vertx.reactivex.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddTrackHandler implements Handler {

    private static final Logger logger = LoggerFactory.getLogger(AddTrackHandler.class);


    private static final String PATH = "/track";
    private AddTrack addTrack;

    public AddTrackHandler(AddTrack addTrack) {

        this.addTrack = addTrack;
    }

    @Override
    public void register(Router router) {
        router.post(PATH).handler(this::handle);
    }

    private void handle(RoutingContext context) {
        TrackDTO trackDTO = TrackDTO.buildFrom(context.getBodyAsJson());

        addTrack.execute(trackDTO)
                .subscribe(() -> onSuccess(context), error -> onError(context, error));
    }

    private void onError(RoutingContext context, Throwable error) {
        logger.error(error.getLocalizedMessage(), error);

        context.response()
                .putHeader("Content-Type", "application/json")
                .setStatusCode(500)
                .end(error.getLocalizedMessage());
    }

    private void onSuccess(RoutingContext context) {
        context.response()
                .setStatusCode(201)
                .end();
    }

}

