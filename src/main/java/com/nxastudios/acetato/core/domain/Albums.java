package com.nxastudios.acetato.core.domain;

import io.reactivex.Completable;
import io.reactivex.Single;

import java.util.Map;

public interface Albums {
    Completable put(Album album);
    Single<Album> getOne(String idAlbum);
    Single<Map<String,Album>> list();
    Completable remove(String idAlbum);
}
