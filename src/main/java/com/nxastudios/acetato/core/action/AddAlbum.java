package com.nxastudios.acetato.core.action;

import com.nxastudios.acetato.core.domain.Album;
import com.nxastudios.acetato.core.domain.Albums;
import com.nxastudios.acetato.core.infrastructure.repositories.services.converter.AlbumDTO;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.vertx.core.json.JsonObject;

public class AddAlbum {
    private Albums albumsRepository;

    public AddAlbum(Albums albumsRepository) {
        this.albumsRepository = albumsRepository;
    }

    public Completable execute(AlbumDTO data) {
//        return buildAlbumFrom(data)
//                .flatMapCompletable(album -> albumsRepository.put(album));
        return null;

    }

}
