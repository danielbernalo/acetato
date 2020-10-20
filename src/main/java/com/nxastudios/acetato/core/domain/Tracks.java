package com.nxastudios.acetato.core.domain;

import com.nxastudios.acetato.core.domain.Track;
import io.reactivex.Completable;
import io.reactivex.Single;

import java.util.Map;

public interface Tracks {
    Completable put(Track track);
    Completable remove(String idTrack);
    Single<Track> getOne(String idTrack);
    Single<Map<String, Track>> list();
}
