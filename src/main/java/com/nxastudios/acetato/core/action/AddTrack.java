package com.nxastudios.acetato.core.action;

import com.nxastudios.acetato.core.domain.Track;
import com.nxastudios.acetato.core.domain.Tracks;
import com.nxastudios.acetato.core.infrastructure.repositories.services.converter.TrackDTO;
import io.reactivex.Completable;
import io.reactivex.Single;

public class AddTrack {

    private Tracks tracks;

    public AddTrack(Tracks tracks) {
        this.tracks = tracks;
    }

    public Completable execute(TrackDTO trackDTO) {
        return null;
//        return buildTrackFrom(trackDTO)
//                .flatMapCompletable(track -> tracks.put(track));
    }

//    private Single<Track> buildTrackFrom(TrackDTO data) {
//        return Single.just(
//                new Track.Builder()
//                        .withTitle(data.title())
//                        .withTrackNumber(data.trackNumber())
//                        .withDuration(data.duration())
//                        .withDiscNumber(data.discNumber())
//                        .withAlbum(data.album())
//                        .withArtists(data.artists())
//                        .withIdTrack(data.id())
//                        .build()
//        );
//    }
}
