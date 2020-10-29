package com.nxastudios.acetato.core.action;

import com.nxastudios.acetato.core.domain.Track;
import com.nxastudios.acetato.core.domain.TrackId;
import com.nxastudios.acetato.core.domain.Tracks;
import io.reactivex.Single;

public class GetTrack {
    private Tracks tracksRepository;

    public GetTrack(Tracks tracksRepository) {
        this.tracksRepository = tracksRepository;
    }

    public Single<Track> execute(String idTrack) {
        return tracksRepository.getOne(new TrackId(idTrack));
    }
}
