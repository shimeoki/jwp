package io.github.shimeoki.jwp.app;

public class NotFoundException extends RuntimeException {

    public NotFoundException(
            final String entity,
            final String with,
            final String value) {

        super(String.format("%s with %s '%s' not found",
                entity, with, value));
    }
}
