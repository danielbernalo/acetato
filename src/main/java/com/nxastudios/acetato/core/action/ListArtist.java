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

    public Single<Map<String, ArtistDTO>> execute() {
        return artistsRepository.list()
                .map(artistIdArtistMap -> buildArtistDTOFrom(artistIdArtistMap));
    }

    private Map<String, ArtistDTO> buildArtistDTOFrom(Map<ArtistId, Artist> artistIdArtistMap) {
        String id = artistIdArtistMap.entrySet().stream().findFirst().get().getKey().get();
        Artist artist = artistIdArtistMap.entrySet().stream().findFirst().get().getValue();
        ArtistDTO artistDTO = new ArtistDTO(
                artist.getArtistId(),
                artist.getName()
        );
        return Map.of(id, artistDTO);
    }

}
