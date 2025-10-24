package io.github.shimeoki.jwp.cli;

public final class Runners {

    public static Runner exactArgs(final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("invalid length");
        }

        return (args) -> {
            if (args.length != n) {
                throw new IllegalArgumentException(String.format(
                        "expected %d args, got %d", n, args.length));
            }
        };
    }
}
