package io.github.shimeoki.os4j;

public enum Type {

    WINDOWS,
    UNIX,
    DARWIN;

    private static Type current;

    private static Type cache() {
        final var os = System.getProperty("os.name").toLowerCase();
        if (os.contains("darwin") || os.contains("mac")) {
            return DARWIN;
        } else if (os.contains("win")) {
            return WINDOWS;
        } else if (os.contains("nux") || os.contains("nix")) {
            return UNIX;
        } else {
            throw new IllegalStateException("unsupported os type");
        }
    }

    public static Type current() {
        if (current == null) {
            current = cache();
        }

        return current;
    }
}
