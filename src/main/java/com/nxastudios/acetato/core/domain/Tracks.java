package com.nxastudios.acetato.core.domain;

import io.reactivex.Completable;
import io.reactivex.Single;

import java.util.List;

public interface Tracks {
    Completable put(Track track);

    Completable remove(TrackId trackId);

    Single<Track> getOne(TrackId trackId);

    Single<List<Track>> list();
}
