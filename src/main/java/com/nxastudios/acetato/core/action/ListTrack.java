package com.nxastudios.acetato.core.action;

import com.nxastudios.acetato.core.domain.Track;
import com.nxastudios.acetato.core.domain.Tracks;
import io.reactivex.Single;

import java.util.List;

public class ListTrack {
    private Tracks tracksRepository;

    public ListTrack(Tracks tracksRepository) {
        this.tracksRepository = tracksRepository;
    }

    public Single<List<Track>> execute() {
        return tracksRepository.list();
    }
}
