package io.github.shimeoki.jwp.config;

import java.nio.file.Path;
import java.nio.file.Paths;

// TODO: check for nulls or use defaults instead of nulls

public record Config(DB db, Store store) {
    public record DB(Type type) {
        public enum Type {
            INMEMORY,
        }
    }

    public record Store(String path, Algorithm algorithm) {
        public enum Algorithm {
            SHA256,
        }
    }

    private enum OS {
        WINDOWS,
        UNIX,
        DARWIN,
    }

    // TODO: probably better to move somewhere else
    private static OS os() {
        final var os = System.getProperty("os.name").toLowerCase();
        if (os.contains("darwin") || os.contains("mac")) {
            return OS.DARWIN;
        } else if (os.contains("win")) {
            return OS.WINDOWS;
        } else if (os.contains("nux") || os.contains("nix")) {
            return OS.UNIX;
        } else {
            throw new IllegalStateException("unsupported os");
        }
    }

    // rewrite of https://pkg.go.dev/os#UserConfigDir
    private static String configDir() {
        Path root = null;
        switch (os()) {
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
            throw new IllegalStateException("config dir is unavailable");
        }

        // TODO: change dir to wp when jdbc for data sharing
        return root.resolve("jwp").toString();
    }

    public static Config defaults() {
        return new Config(
                new DB(DB.Type.INMEMORY),
                new Store(configDir(), Store.Algorithm.SHA256));
    }
}
