package com.nxastudios.acetato.core.action;

import com.nxastudios.acetato.core.domain.Artists;
import io.reactivex.Completable;

public class DeleteArtist {
    private Artists artists;

    public DeleteArtist(Artists artists) {
        this.artists = artists;
    }

    public Completable execute(String idArtist) {
        return artists.remove(idArtist);
    }
}
