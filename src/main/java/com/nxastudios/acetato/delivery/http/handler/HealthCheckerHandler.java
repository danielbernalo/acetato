package com.nxastudios.acetato.delivery.http.handler;

import io.vertx.reactivex.ext.web.Router;
import io.vertx.reactivex.ext.web.RoutingContext;

public class HealthCheckerHandler implements Handler {
    private static final String PATH = "/health";

    @Override
    public void register(Router router) {
        router.get(PATH).handler(this::handle);

    }

    private void handle(RoutingContext context) {
        context.response().setStatusCode(200).end("OK");
    }
}
