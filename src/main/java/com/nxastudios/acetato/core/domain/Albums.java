package com.nxastudios.acetato.core.domain;

import io.reactivex.Completable;

public interface Albums {
    Completable put(Album album);

}
