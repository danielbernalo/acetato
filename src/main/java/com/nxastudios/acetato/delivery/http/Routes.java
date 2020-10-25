package com.nxastudios.acetato.delivery.http;

import com.nxastudios.acetato.delivery.http.handler.*;
import com.nxastudios.acetato.delivery.http.provider.Actions;
import io.vertx.reactivex.ext.web.Router;

public class Routes {
    public static void register(Router router) {
        new AddAlbumHandler(Actions.addAlbum).register(router);

        new AddArtistsHandler(Actions.addArtist).register(router);

        new ListArtistHandler(Actions.listArtist).register(router);

        new GetArtistHandler(Actions.getArtist).register(router);
    }
}
