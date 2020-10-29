package com.nxastudios.acetato.delivery.http.provider;

import com.nxastudios.acetato.core.action.*;

public class Actions {

    //artist
    public static AddArtist addArtist;
    public static ListArtist listArtist;
    public static GetArtist getArtist;
    public static DeleteArtist deleteArtist;
    public static UpdateArtist updateArtist;
    public static AddAlbum addAlbum;
    public static ListAlbum listAlbum;
    public static GetAlbum getAlbum;
    public static DeleteAlbum deleteAlbum;
    public static UpdateAlbum updateAlbum;
    public static AddTrack addTrack;
    public static ListTrack listTrack;
    public static GetTrack getTrack;
    public static DeleteTrack deleteTrack;
    public static UpdateTrack updateTrack;

    static {

        //artist
        addArtist = new AddArtist(Repositories.artists);
        listArtist = new ListArtist(Repositories.artists);
        getArtist = new GetArtist(Repositories.artists);
        deleteArtist = new DeleteArtist(Repositories.artists);
        updateArtist = new UpdateArtist(Repositories.artists);

        //album
        addAlbum = new AddAlbum(Repositories.albums);
        listAlbum = new ListAlbum(Repositories.albums);
        getAlbum = new GetAlbum(Repositories.albums);
        deleteAlbum = new DeleteAlbum(Repositories.albums);
        updateAlbum = new UpdateAlbum(Repositories.albums);

        //track
        addTrack = new AddTrack(Repositories.tracks);
        listTrack = new ListTrack(Repositories.tracks);
        getTrack = new GetTrack(Repositories.tracks);
        deleteTrack = new DeleteTrack(Repositories.tracks);
        updateTrack = new UpdateTrack(Repositories.tracks);
    }
}
