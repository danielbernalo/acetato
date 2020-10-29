package com.nxastudios.acetato.core.action;

import com.nxastudios.acetato.core.domain.TrackId;
import com.nxastudios.acetato.core.domain.Tracks;
import io.reactivex.Completable;

public class DeleteTrack {
    private Tracks tracks;

    public DeleteTrack(Tracks tracks) {
        this.tracks = tracks;
    }

    public Completable execute(String trackId) {
        return tracks.remove(new TrackId(trackId));
    }
}
