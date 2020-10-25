package com.nxastudios.acetato.core.domain;

import com.fasterxml.jackson.annotation.JsonValue;

public class TrackId {
    private String value;

    public TrackId(String value) {
        this.value = value != null ? value : "";
    }
    @Override
    @JsonValue
    public String toString() {
        return value;
    }
}
