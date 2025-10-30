package io.github.shimeoki.jwp.config;

public interface Transaction {

    void commit();

    void rollback();
}
