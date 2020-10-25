package com.nxastudios.acetato.delivery.http.provider;

import com.nxastudios.acetato.core.action.*;

public class Actions {

    //album
    public static AddAlbum addAlbum;


    //artist
    public static AddArtist addArtist;
    public static ListArtist listArtist;
    public static GetArtist getArtist;

    //track
    public static AddTrack addTrack;

    static {
        addAlbum = new AddAlbum(Repositories.albums);

        addArtist = new AddArtist(Repositories.artists);
        listArtist = new ListArtist(Repositories.artists);
        getArtist = new GetArtist(Repositories.artists);

        addTrack = new AddTrack(Repositories.tracks);
    }
}
