package com.nxastudios.acetato.core.domain;

public class AlbumId {
    private String value;

    public AlbumId(String value) {
        this.value = value != null ? value : "";
    }

    public String get() {
        return value;
    }
}