package com.nxastudios.acetato.core.action;

import com.nxastudios.acetato.core.domain.Artist;
import com.nxastudios.acetato.core.domain.ArtistId;
import com.nxastudios.acetato.core.domain.Artists;
import com.nxastudios.acetato.core.infrastructure.repositories.services.converter.ArtistDTO;
import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.Single;

public class GetArtist {
    private Artists artistsRepository;

    public GetArtist(Artists artistsRepository) {
        this.artistsRepository = artistsRepository;
    }

    public Single<Artist> execute(String artistId) {
        return artistsRepository.getOne(new ArtistId(artistId));
    }
}
