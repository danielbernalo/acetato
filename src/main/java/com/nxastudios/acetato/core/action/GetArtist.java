package com.nxastudios.acetato.core.action;

import com.nxastudios.acetato.core.domain.Artist;
import com.nxastudios.acetato.core.domain.ArtistId;
import com.nxastudios.acetato.core.domain.Artists;
import io.reactivex.Single;

public class GetArtist {
    private Artists artistsRepository;

    public GetArtist(Artists artistsRepository) {
        this.artistsRepository = artistsRepository;
    }

    public Single<Artist> execute(ArtistId artistId) {
        return artistsRepository.getOne(artistId);
    }
}
