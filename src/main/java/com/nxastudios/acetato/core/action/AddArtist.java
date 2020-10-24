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

        return buildArtistFrom(artistDTO)
                .flatMapCompletable(artist -> artists.put(artist));
    }

    private Single<Artist> buildArtistFrom(ArtistDTO data) {
        return Single.just(
                new Artist.Builder()
                        .withName(data.name())
                        .withId(data.artistId())
                        .build()
        );
    }
}
