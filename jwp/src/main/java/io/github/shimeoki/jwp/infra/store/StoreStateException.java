package io.github.shimeoki.jwp.infra.store;

public class StoreStateException extends RuntimeException {

    public StoreStateException(final String message) {
        super(message);
    }

    public StoreStateException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
