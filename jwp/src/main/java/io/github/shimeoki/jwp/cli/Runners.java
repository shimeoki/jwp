package io.github.shimeoki.jwp.cli;

public final class Runners {

    public static Runner exactArgs(final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("invalid length");
        }

        return (args) -> {
            if (args.length != n) {
                throw new InvalidArgsException(n, args.length);
            }
        };
    }

    public static Runner rangeArgs(final int min, final int max) {
        if (max < min) {
            throw new IllegalArgumentException("'max' is less than 'min'");
        }

        if (min < 0) {
            throw new IllegalArgumentException("'min' shouldn't be negative");
        }

        return (args) -> {
            if (args.length < min || args.length > max) {
                throw new InvalidArgsException(min, max, args.length);
            }
        };
    }
}
