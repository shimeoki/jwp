package io.github.shimeoki.jwp.domain.values;

public class InvalidFormatException extends RuntimeException {

    public InvalidFormatException(final String format) {
        super(String.format("invalid or unsupported format: %s", format));
    }
}
