package io.github.shimeoki.jwp.cli;

public class InvalidArgsException extends RuntimeException {

    public InvalidArgsException(final int expected, final int got) {
        super(String.format("expected %d args, got %d", expected, got));
    }

    public InvalidArgsException(final int min, final int max, final int got) {
        super(String.format("expected between %d and %d args, got %d",
                min, max, got));
    }
}
