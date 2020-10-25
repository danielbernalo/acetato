package com.nxastudios.acetato.delivery.http.handler;

import io.vertx.reactivex.ext.web.Router;

public interface Handler {
    void register(Router router);
}
