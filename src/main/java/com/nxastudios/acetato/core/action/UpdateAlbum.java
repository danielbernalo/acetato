package com.nxastudios.acetato.core.action;

import com.nxastudios.acetato.core.domain.Album;
import com.nxastudios.acetato.core.domain.Albums;
import io.reactivex.Completable;

public class UpdateAlbum {

    private Albums albumsRepository;

    public UpdateAlbum(Albums albumsRepository) {
        this.albumsRepository = albumsRepository;
    }

    public Completable execute(Album album) {
        return albumsRepository.put(album);
    }
}
