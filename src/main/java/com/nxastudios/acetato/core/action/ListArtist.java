package com.nxastudios.acetato.core.action;

import com.nxastudios.acetato.core.domain.Artist;
import com.nxastudios.acetato.core.domain.ArtistId;
import com.nxastudios.acetato.core.domain.Artists;
import com.nxastudios.acetato.core.infrastructure.repositories.services.converter.ArtistDTO;
import io.reactivex.Single;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ListArtist {
    private Artists artistsRepository;

    public ListArtist(Artists artistsRepository) {
        this.artistsRepository = artistsRepository;
    }

    public Single<Map<ArtistId, Artist>> execute() {
        return artistsRepository.list();
    }
}
