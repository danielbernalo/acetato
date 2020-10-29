package com.nxastudios.acetato.core.action;

import com.nxastudios.acetato.core.domain.Artist;
import com.nxastudios.acetato.core.domain.Artists;
import com.nxastudios.acetato.core.infrastructure.services.converter.ArtistDTO;
import io.reactivex.Completable;

public class UpdateArtist {
    private Artists repository;

    public UpdateArtist(Artists repository) {
        this.repository = repository;
    }

    public Completable execute(ArtistDTO artist) {
        return repository.put(new Artist(artist));
    }
}
