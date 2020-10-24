package com.nxastudios.acetato.core.domain;

public class TrackId {
    private String value;

    public TrackId(String value) {
        this.value = value != null ? value : "";
    }

    public String get() {
        return value;
    }
}
