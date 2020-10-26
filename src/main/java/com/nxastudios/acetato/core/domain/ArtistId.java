package com.nxastudios.acetato.core.domain;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArtistId artistId = (ArtistId) o;
        return Objects.equals(value, artistId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
