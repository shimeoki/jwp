package io.github.shimeoki.os4j;

public class UnsupportedTypeException extends RuntimeException {

    public UnsupportedTypeException(final String type) {
        super(String.format("unsupported os type: %s", type));
    }
}
