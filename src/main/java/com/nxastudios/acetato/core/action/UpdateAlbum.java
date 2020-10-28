package com.nxastudios.acetato.core.action;

import com.nxastudios.acetato.core.domain.Album;
import com.nxastudios.acetato.core.domain.Albums;
import com.nxastudios.acetato.core.infrastructure.services.converter.AlbumDTO;
import io.reactivex.Completable;

public class UpdateAlbum {

    private Albums albumsRepository;

    public UpdateAlbum(Albums albumsRepository) {
        this.albumsRepository = albumsRepository;
    }

    public Completable execute(AlbumDTO album) {
        return albumsRepository.put(new Album(album));
    }
}
