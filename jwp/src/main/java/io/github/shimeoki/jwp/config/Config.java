package io.github.shimeoki.jwp.config;

import io.github.shimeoki.os4j.User;

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

    private static String storePath() {
        return User.configDir().resolve("jwp", "store").toString();
    }

    public static Config defaults() {
        return new Config(
                new DB(DB.Type.INMEMORY),
                new Store(storePath(), Store.Algorithm.SHA256));
    }
}
