package io.github.shimeoki.jwp.config;

// TODO: check for nulls or use defaults instead of nulls

public record Config(DB db) {
    public record DB(Type type) {
        public enum Type {
            INMEMORY
        }
    }

    public static Config defaults() {
        return new Config(new DB(DB.Type.INMEMORY));
    }
}
