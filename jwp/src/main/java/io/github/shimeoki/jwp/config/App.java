package io.github.shimeoki.jwp.config;

import java.io.IOException;
import java.util.Objects;

import io.github.shimeoki.jwp.app.Worker;
import io.github.shimeoki.jwp.config.workers.LocalInMemoryWorker;
import io.github.shimeoki.jwp.domain.values.InvalidAlgorithmException;
import io.github.shimeoki.jwp.infra.db.inmemory.Database;
import io.github.shimeoki.jwp.infra.store.Hasher;
import io.github.shimeoki.jwp.infra.store.Hashers;
import io.github.shimeoki.jwp.infra.store.LocalStore;
import io.github.shimeoki.jwp.infra.store.StoreStateException;

public final class App {

    private Config cfg;

    private Database db;
    private LocalStore store;

    private Worker<Provider> worker;
    private Handlers handlers;

    public App(final Config cfg) {
        setConfig(cfg);
    }

    public void open() {
        db = Database.open();

        final var storeConfig = config().store();

        Hasher hasher;
        if (storeConfig.algorithm() == Config.Store.Algorithm.SHA256) {
            hasher = Hashers.sha256();
        } else {
            throw new InvalidAlgorithmException(
                    storeConfig.algorithm().toString());
        }

        try {
            store = new LocalStore(storeConfig.path(), hasher);
            if (store.count() > 0) {
                throw new StoreStateException(
                        "the store should be empty with inmemory db");
            }
        } catch (final IOException e) {
            throw new StoreStateException("failed to open the store", e);
        }

        worker = new LocalInMemoryWorker(db, store);
        handlers = Handlers.fromWorker(worker);
    }

    public Handlers handlers() {
        return handlers;
    }

    public void setConfig(final Config cfg) {
        this.cfg = Objects.requireNonNull(cfg);
    }

    public Config config() {
        return cfg;
    }

    public void close() {
        try {
            store.clear();
        } catch (final IOException e) {
            throw new StoreStateException("failed to clear the store", e);
        }

        db = null;
        store = null;

        worker = null;
        handlers = null;
    };
}
