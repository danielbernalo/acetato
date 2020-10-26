package com.nxastudios.acetato.config;

import java.util.Map;

public class Environment {
    public static final Integer PORT;
    public static final String REPOSITORY;
    public static final String MONGO_URI;

    static {
        Map<String, String> env = System.getenv();
        PORT = Integer.valueOf(env.getOrDefault("PORT", "8080"));
        REPOSITORY = env.getOrDefault("REPOSITORY", "MEMORY");
        MONGO_URI = env.getOrDefault("MONGO_URI", "");

    }
}

