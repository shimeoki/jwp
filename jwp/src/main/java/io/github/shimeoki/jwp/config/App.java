package io.github.shimeoki.jwp.config;

import io.github.shimeoki.jwp.infra.inmemory.Database;

public final class App {

    private Database db;
    private Worker worker;

    private Handlers handlers;

    public App() {
        super();
    }

    public void open() {
        db = Database.open();
        worker = new Worker(db);

        handlers = worker.handlers();
    }

    public Handlers handlers() {
        return handlers;
    }

    public void close() {
        // TODO: implement when store
    };
}
