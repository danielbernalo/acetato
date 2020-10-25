package com.nxastudios.acetato.delivery.http;

import com.nxastudios.acetato.config.Environment;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.core.http.HttpServer;
import io.vertx.reactivex.ext.web.Router;
import io.vertx.reactivex.ext.web.handler.BodyHandler;

public class ServerVerticle extends AbstractVerticle {
    private Router router;

    @Override
    public void start() throws Exception {
        router = createRouter();
        settingRoutes(router);
        startHttpServer(router);
    }

    private void startHttpServer(Router router) {
        vertx.createHttpServer()
                .requestHandler(router)
                .rxListen(Environment.PORT)
                .subscribe(this::onServerStarted, this::onServerStartError);
    }

    private void onServerStarted(HttpServer httpServer) {
        System.out.println("Made with ♥ by Daniel Bernal");
        System.out.println(("Server listening on :" + httpServer.actualPort()));
    }

    private void onServerStartError(Throwable error) {
        System.out.println("Server not started");
        System.out.println(error.getLocalizedMessage());
    }

    private Router createRouter() {
        Router router = Router.router(vertx);
        router.route()
                .handler(BodyHandler.create());
        return router;
    }

    private void settingRoutes(Router router) {
        Routes.register(router);
    }
}

