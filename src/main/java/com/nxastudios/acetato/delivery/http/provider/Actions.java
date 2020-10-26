package com.nxastudios.acetato.delivery.http.provider;

import com.nxastudios.acetato.core.action.*;

public class Actions {

    //artist
    public static AddArtist addArtist;
    public static ListArtist listArtist;
    public static GetArtist getArtist;
    public static DeleteArtist deleteArtist;

    static {

        //artist
        addArtist = new AddArtist(Repositories.artists);
        listArtist = new ListArtist(Repositories.artists);
        getArtist = new GetArtist(Repositories.artists);
        deleteArtist= new DeleteArtist(Repositories.artists);
    }
}
