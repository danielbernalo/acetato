package com.nxastudios.acetato.core.action;

import com.nxastudios.acetato.core.domain.Artist;
import com.nxastudios.acetato.core.domain.Artists;
import io.reactivex.Completable;

public class AddArtist {
    private Artists artists;

    public AddArtist(Artists artists) {
        this.artists = artists;
    }

    public Completable execute(Artist artist) {
        return artists.put(artist);
    }
}
