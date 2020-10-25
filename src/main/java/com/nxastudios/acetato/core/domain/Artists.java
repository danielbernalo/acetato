package com.nxastudios.acetato.core.domain;

import io.reactivex.Completable;
import io.reactivex.Single;

import java.util.List;

public interface Artists {
    Completable put(Artist artist);

    Completable remove(ArtistId artistId);

    Single<Artist> getOne(ArtistId artistId);

    Single<List<Artist>> list();
}
