package io.github.shimeoki.os4j;

public class UnavailableDirectoryException extends RuntimeException {

    public UnavailableDirectoryException(final String type) {
        super(String.format("%s directory is unavailable", type));
    }
}
