package com.nxastudios.acetato.core.action;

import com.nxastudios.acetato.core.domain.Track;
import com.nxastudios.acetato.core.domain.Tracks;
import io.reactivex.Completable;

public class AddTrack {

    private Tracks tracks;

    public AddTrack(Tracks tracks) {
        this.tracks = tracks;
    }

    public Completable execute(Track track) {
        return tracks.put(track);
    }
}
