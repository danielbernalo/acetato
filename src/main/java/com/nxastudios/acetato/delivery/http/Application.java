package com.nxastudios.acetato.delivery.http;

import io.reactivex.plugins.RxJavaPlugins;
import io.vertx.reactivex.core.RxHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.nxastudios.acetato.delivery.http.provider.VertxProvider.vertx;

public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        configureRxJavaPlugins();
        vertx.deployVerticle(ServerVerticle.class.getCanonicalName());
    }

    private static void configureRxJavaPlugins() {
        RxHelper.blockingScheduler(vertx, false);
        RxJavaPlugins.setIoSchedulerHandler(scheduler -> RxHelper.blockingScheduler(vertx));
        RxJavaPlugins.setNewThreadSchedulerHandler(scheduler -> RxHelper.scheduler(vertx));
        RxJavaPlugins.setSingleSchedulerHandler(scheduler -> RxHelper.scheduler(vertx));
        RxJavaPlugins.setErrorHandler((error) -> logger.error("Unhandled exception: " + error.getLocalizedMessage()));
    }
}
