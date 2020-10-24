package com.nxastudios.acetato.core.domain;

public class ArtistId {
    private String value;

    public ArtistId(String value) {
        this.value = value != null ? value : "";
    }

    public String get() {
        return value;
    }
}
