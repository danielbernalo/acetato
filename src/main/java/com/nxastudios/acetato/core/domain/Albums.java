package com.nxastudios.acetato.core.domain;

import io.reactivex.Completable;
import io.reactivex.Single;

import java.util.List;

public interface Albums {
    Completable put(Album album);

    Completable remove(AlbumId albumId);

    Single<Album> getOne(AlbumId albumId);

    Single<List<Album>> list();
}
