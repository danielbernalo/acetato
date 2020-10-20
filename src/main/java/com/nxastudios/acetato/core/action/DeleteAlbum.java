package com.nxastudios.acetato.core.action;

import com.nxastudios.acetato.core.domain.AlbumId;
import com.nxastudios.acetato.core.domain.Albums;
import io.reactivex.Completable;

public class DeleteAlbum {
    private Albums albumsRepository;

    public DeleteAlbum(Albums albumsRepository) {
        this.albumsRepository = albumsRepository;
    }


    public Completable execute(AlbumId idAlbum) {
        return albumsRepository.remove(idAlbum);
    }
}
