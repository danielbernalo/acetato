package com.nxastudios.acetato.core.action;

import com.nxastudios.acetato.core.domain.Artist;
import com.nxastudios.acetato.core.domain.Artists;
import com.nxastudios.acetato.core.infrastructure.repositories.services.converter.ArtistDTO;
import io.reactivex.Completable;
import io.reactivex.Single;

public class AddArtist {
    private Artists artists;

    public AddArtist(Artists artists) {
        this.artists = artists;
    }

    public Completable execute(ArtistDTO artistDTO) {
        return artists.put(new Artist(artistDTO));
    }
}
