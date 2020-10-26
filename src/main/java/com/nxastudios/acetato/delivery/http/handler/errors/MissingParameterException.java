package com.nxastudios.acetato.delivery.http.handler.errors;

public class MissingParameterException extends Exception {
    public MissingParameterException(String id) {
        super("Parameter: " + id + " is required.");
    }
}
