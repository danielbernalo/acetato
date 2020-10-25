package com.nxastudios.acetato.core.domain;

import com.fasterxml.jackson.annotation.JsonValue;

public class AlbumId {
    private String value;

    public AlbumId(String value) {
        this.value = value != null ? value : "";
    }

    @Override
    @JsonValue
    public String toString() {
        return value;
    }
}
