package io.github.shimeoki.jwp.app;

public class InvalidRelationException extends RuntimeException {

    public InvalidRelationException(
            final String entity, final boolean attached) {

        String state;
        if (attached) {
            state = "already attached";
        } else {
            state = "not attached";
        }

        super(String.format("%s %s", entity, state));
    }
}
