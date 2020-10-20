package com.nxastudios.acetato.core.domain;

import io.reactivex.Completable;
import io.reactivex.Single;

import java.util.Map;

public interface Albums {
    Completable put(Album album);
    Single<Album> getOne(AlbumId idAlbum);
    Single<Map<AlbumId,Album>> list();
    Completable remove(AlbumId idAlbum);
}
