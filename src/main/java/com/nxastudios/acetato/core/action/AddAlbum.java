package com.nxastudios.acetato.core.action;

import com.nxastudios.acetato.core.domain.Album;
import com.nxastudios.acetato.core.domain.Albums;
import com.nxastudios.acetato.core.infrastructure.services.converter.AlbumDTO;
import io.reactivex.Completable;

public class AddAlbum {
    private Albums albumsRepository;

    public AddAlbum(Albums albumsRepository) {
        this.albumsRepository = albumsRepository;
    }

    public Completable execute(AlbumDTO albumDTO) {
        return this.albumsRepository.put(new Album(albumDTO));
    }

}
