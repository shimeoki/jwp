package io.github.shimeoki.jwp.app;

public interface Provider extends AutoCloseable {

    void commit();
}
