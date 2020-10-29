package com.nxastudios.acetato.delivery.http;

import com.nxastudios.acetato.delivery.http.handler.*;
import com.nxastudios.acetato.delivery.http.provider.Actions;
import io.vertx.reactivex.ext.web.Router;

public class Routes {
    public static void register(Router router) {
        new HealthCheckerHandler().register(router);

        //artist
        new AddArtistsHandler(Actions.addArtist).register(router);
        new ListArtistHandler(Actions.listArtist).register(router);
        new GetArtistHandler(Actions.getArtist).register(router);
        new DeleteArtistHandler(Actions.deleteArtist).register(router);
        new UpdateArtistsHandler(Actions.updateArtist).register(router);

        //album
        new AddAlbumHandler(Actions.addAlbum).register(router);
        new ListAlbumHandler(Actions.listAlbum).register(router);
        new GetAlbumHandler(Actions.getAlbum).register(router);
        new DeleteAlbumHandler(Actions.deleteAlbum).register(router);
        new UpdateAlbumHandler(Actions.updateAlbum).register(router);

        //track
        new AddTrackHandler(Actions.addTrack).register(router);
        new ListTrackHandler(Actions.listTrack).register(router);
        new GetTrackHandler(Actions.getTrack).register(router);
        new DeleteTrackHandler(Actions.deleteTrack).register(router);
        new UpdateTrackHandler(Actions.updateTrack).register(router);

    }
}
