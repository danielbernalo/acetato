package com.nxastudios.acetato.core.domain;

import java.util.Objects;

public class TrackId {
    private String value;

    public TrackId(String value) {

        this.value = value != null ? value : "";
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrackId trackId = (TrackId) o;
        return Objects.equals(value, trackId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
