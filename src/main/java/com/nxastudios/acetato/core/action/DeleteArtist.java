package com.nxastudios.acetato.core.action;

import com.nxastudios.acetato.core.domain.ArtistId;
import com.nxastudios.acetato.core.domain.Artists;
import io.reactivex.Completable;

public class DeleteArtist {
    private Artists artists;

    public DeleteArtist(Artists artists) {
        this.artists = artists;
    }

    public Completable execute(String artistId) {
        return artists.remove(new ArtistId(artistId));
    }
}
