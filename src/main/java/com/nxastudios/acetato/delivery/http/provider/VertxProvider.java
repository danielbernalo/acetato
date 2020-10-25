package com.nxastudios.acetato.delivery.http.provider;

import io.vertx.reactivex.core.Vertx;

public class VertxProvider {
    public static final Vertx vertx;

    static {
        vertx = Vertx.vertx();
    }

}
