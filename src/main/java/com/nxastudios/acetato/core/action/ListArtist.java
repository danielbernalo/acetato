package com.nxastudios.acetato.core.action;

import com.nxastudios.acetato.core.domain.Artist;
import com.nxastudios.acetato.core.domain.ArtistId;
import com.nxastudios.acetato.core.domain.Artists;
import io.reactivex.Single;

import java.util.Map;

public class ListArtist {
    private Artists artistsRepository;

    public ListArtist(Artists artistsRepository) {
        this.artistsRepository = artistsRepository;
    }

    public Single<Map<ArtistId, Artist>> execute() {
        return artistsRepository.list();
    }
}
