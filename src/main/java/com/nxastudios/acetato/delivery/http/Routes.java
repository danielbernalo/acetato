package com.nxastudios.acetato.delivery.http;

import com.nxastudios.acetato.delivery.http.handler.HealthCheckerHandler;
import io.vertx.reactivex.ext.web.Router;

public class Routes {
    public static void register(Router router) {
        new HealthCheckerHandler().register(router);
    }
}
