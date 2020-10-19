package com.nxastudios.acetato.core.domain;

public class Artist {
    private final String name;

    private Artist(String name) {
        this.name = name;
    }

    public static class Builder {
        private String name;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Artist build() {
            return new Artist(name);
        }
    }
}
