package io.github.shimeoki.jwp.cli;

public final class Runners {

    public static Runner exactArgs(final int n, final Runner r) {
        return (cmd, args) -> {
            if (args.length != n) {
                throw new IllegalArgumentException(String.format(
                        "expected %d args, got %d", n, args.length));
            }

            r.run(cmd, args);
        };
    }
}
