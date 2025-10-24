package io.github.shimeoki.jwp.config;

import java.util.Objects;

import io.github.shimeoki.jwp.infra.db.inmemory.Database;
import io.github.shimeoki.jwp.infra.db.inmemory.TagRepository;

public final class Worker
        implements io.github.shimeoki.jwp.app.Worker<Provider> {

    private final Database db; // TODO: change when jdbc

    public Worker(final Database db) {
        this.db = Objects.requireNonNull(db);
    }

    @Override
    public Provider work() {
        final var repos = new Repositories(
                new TagRepository(db),
                null); // TODO: add when wallpaper inmemory repo

        return new Provider(repos, null);
    }
}
