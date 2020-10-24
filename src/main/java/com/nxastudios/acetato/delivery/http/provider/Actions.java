package com.nxastudios.acetato.delivery.http.provider;

import com.nxastudios.acetato.core.action.AddAlbum;
import com.nxastudios.acetato.core.action.AddArtist;
import com.nxastudios.acetato.core.action.AddTrack;
import com.nxastudios.acetato.core.action.ListArtist;

public class Actions {

    //album
    public static AddAlbum addAlbum;


    //artist
    public static AddArtist addArtist;
    public static ListArtist listArtist;

    //track
    public static AddTrack addTrack;

    static {
        addAlbum = new AddAlbum(Repositories.albums);

        addArtist = new AddArtist(Repositories.artists);
        listArtist = new ListArtist(Repositories.artists);

        addTrack = new AddTrack(Repositories.tracks);
    }
}
