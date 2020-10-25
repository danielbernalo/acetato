package com.nxastudios.acetato.core.domain;

import com.fasterxml.jackson.annotation.JsonValue;

public class ArtistId {
    private String value;

    public ArtistId(String value) {
        this.value = value != null ? value : "";
    }

    @Override
    @JsonValue
    public String toString() {
        return value;
    }
}
