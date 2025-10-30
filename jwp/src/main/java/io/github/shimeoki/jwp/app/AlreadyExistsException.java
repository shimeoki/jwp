package io.github.shimeoki.jwp.app;

public class AlreadyExistsException extends RuntimeException {

    public AlreadyExistsException(
            final String entity,
            final String with,
            final String value) {

        super(String.format("%s with %s '%s' already exists",
                entity, with, value));
    }
}
