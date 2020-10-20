package com.nxastudios.acetato.core.domain;

import io.reactivex.Completable;
import io.reactivex.Single;

import java.util.Map;

public interface Artists {
    Completable put(Artist artist);

    Completable remove(String idArtist);

    Single<Artist> getOne(String idArtist);

    Single<Map<String, Artist>> list();
}
