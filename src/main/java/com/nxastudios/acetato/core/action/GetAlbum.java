package com.nxastudios.acetato.core.action;

import com.nxastudios.acetato.core.domain.Album;
import com.nxastudios.acetato.core.domain.AlbumId;
import com.nxastudios.acetato.core.domain.Albums;
import io.reactivex.Single;

public class GetAlbum {
    private Albums albumsRepository;

    public GetAlbum(Albums albumsRepository) {
        this.albumsRepository = albumsRepository;
    }

    public Single<Album> execute(String idAlbum) {
        return albumsRepository.getOne(idAlbum);
    }
}
