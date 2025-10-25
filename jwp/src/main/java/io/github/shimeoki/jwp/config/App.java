package io.github.shimeoki.jwp.config;

import java.util.Objects;

import io.github.shimeoki.jwp.app.Worker;
import io.github.shimeoki.jwp.config.workers.LocalInMemoryWorker;
import io.github.shimeoki.jwp.infra.db.inmemory.Database;

public final class App {

    private Config cfg;
    private Database db;
    private Worker<Provider> worker;

    private Handlers handlers;

    public App(final Config cfg) {
        setConfig(cfg);
    }

    public void open() {
        db = Database.open();
        worker = new LocalInMemoryWorker(db);

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
        // TODO: implement when store
    };
}
