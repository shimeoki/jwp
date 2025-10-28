package io.github.shimeoki.jwp.domain.values;

public class InvalidHashException extends RuntimeException {

    public InvalidHashException(final String hash) {
        super(String.format("expected '<algorithm>-<digest>', got '%s'", hash));
    }
}
