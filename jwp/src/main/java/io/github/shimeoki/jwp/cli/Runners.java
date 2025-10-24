package io.github.shimeoki.jwp.cli;

import java.util.Objects;

public final class Runners {

    public static Runner exactArgs(final int n, final Runner r) {
        Objects.requireNonNull(r);

        if (n < 0) {
            throw new IllegalArgumentException("invalid length");
        }

        return (cmd, args) -> {
            if (args.length != n) {
                throw new IllegalArgumentException(String.format(
                        "expected %d args, got %d", n, args.length));
            }

            r.run(cmd, args);
        };
    }
}
