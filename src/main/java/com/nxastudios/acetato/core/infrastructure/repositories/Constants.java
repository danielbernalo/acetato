package com.nxastudios.acetato.core.infrastructure.repositories;

public class Constants {
    public static final String ALBUM_ID;
    public static final String ALBUM_TITLE;
    public static final String ALBUM_RELEASE_DATE;
    public static final String ALBUM_ARTISTS;
    public static final String ALBUM_TRACKS;
    public static final String ALBUM_ALBUM_TYPE;

    public static final String ARTIST_ID;
    public static final String ARTIST_NAME;


    public static final String TRACK_ID;
    public static final String TRACK_TITLE;
    public static final String TRACK_ALBUM;
    public static final String TRACK_ARTISTS;
    public static final String TRACK_DURATION;
    public static final String TRACK_DISC_NUMBER;
    public static final String TRACK_NUMBER;


    static {
        ALBUM_ID = "_id";
        ALBUM_TITLE = "title";
        ALBUM_RELEASE_DATE = "release_date";
        ALBUM_ARTISTS = "artists";
        ALBUM_TRACKS = "tracks";
        ALBUM_ALBUM_TYPE = "type";

        ARTIST_ID = "_id";
        ARTIST_NAME = "name";

        TRACK_ID = "_id";
        TRACK_TITLE = "title";
        TRACK_ALBUM = "album";
        TRACK_ARTISTS = "artists";
        TRACK_DURATION = "duration";
        TRACK_DISC_NUMBER = "disc_number";
        TRACK_NUMBER = "track_number";
    }
}
