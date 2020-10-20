package com.nxastudios.acetato.core.action;

import com.nxastudios.acetato.core.domain.Album;
import com.nxastudios.acetato.core.domain.AlbumId;
import com.nxastudios.acetato.core.domain.Albums;
import io.reactivex.Single;

import java.util.Map;

public class ListAlbum {
    private Albums albumsRepository;

    public ListAlbum(Albums albumsRepository) {
        this.albumsRepository = albumsRepository;
    }

    public Single<Map<AlbumId, Album>> execute() {
        return albumsRepository.list();
    }
}
