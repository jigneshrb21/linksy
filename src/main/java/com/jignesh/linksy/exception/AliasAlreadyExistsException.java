package com.jignesh.linksy.exception;

public class AliasAlreadyExistsException extends RuntimeException {

    public AliasAlreadyExistsException(String alias) {
        super("Alias already in use: " + alias);
    }
}
