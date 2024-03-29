package com.nxastudios.acetato.core.action;

import com.nxastudios.acetato.core.domain.Album;
import com.nxastudios.acetato.core.domain.Albums;
import io.reactivex.Single;

import java.util.List;

public class ListAlbum {
    private Albums albumsRepository;

    public ListAlbum(Albums albumsRepository) {
        this.albumsRepository = albumsRepository;
    }

    public Single<List<Album>> execute() {
        return albumsRepository.list();
    }
}
