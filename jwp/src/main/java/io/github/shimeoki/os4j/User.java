package io.github.shimeoki.os4j;

import java.nio.file.Path;
import java.nio.file.Paths;

public final class User {

    public static Path configDir() {
        Path root = null;
        switch (Type.current()) {
            case WINDOWS:
                root = Paths.get(System.getenv("AppData"));
                break;
            case UNIX:
                final var xdg = System.getenv("XDG_CONFIG_HOME");

                if (xdg == null) {
                    root = Paths.get(System.getenv("HOME"), ".config");
                } else {
                    root = Paths.get(xdg);
                }

                break;
            case DARWIN:
                root = Paths.get(
                        System.getenv("HOME"),
                        "Library",
                        "Application Support");
                break;
        }

        if (root == null) {
            throw new UnavailableDirectoryException("configuration");
        }

        return root;
    }
}
