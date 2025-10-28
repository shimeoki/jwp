package io.github.shimeoki.jwp.config.workers;

import java.util.Objects;

import io.github.shimeoki.jwp.app.Worker;
import io.github.shimeoki.jwp.config.Provider;
import io.github.shimeoki.jwp.config.Storage;
import io.github.shimeoki.jwp.domain.repositories.Store;
import io.github.shimeoki.jwp.infra.db.inmemory.AliasRepository;
import io.github.shimeoki.jwp.infra.db.inmemory.Database;
import io.github.shimeoki.jwp.infra.db.inmemory.SourceRepository;
import io.github.shimeoki.jwp.infra.db.inmemory.TagRepository;
import io.github.shimeoki.jwp.infra.db.inmemory.WallpaperRepository;

public final class LocalInMemoryWorker implements Worker<Provider> {

    private final Database db;
    private final Store store;

    public LocalInMemoryWorker(final Database db, final Store store) {
        this.db = Objects.requireNonNull(db);
        this.store = Objects.requireNonNull(store);
    }

    @Override
    public Provider work() {
        final var s = new Storage(
                new TagRepository(db),
                new WallpaperRepository(db),
                new SourceRepository(db),
                new AliasRepository(db),
                store);

        return new Provider(s, null);
    }
}
