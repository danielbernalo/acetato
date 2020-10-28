package com.nxastudios.acetato.core.domain;

import java.util.Objects;

public class AlbumId {
    private String value;

    public AlbumId(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlbumId albumId = (AlbumId) o;
        return Objects.equals(value, albumId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
