package io.github.shimeoki.jwp.infra.db.inmemory;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import io.github.shimeoki.jwp.domain.entities.Source;
import io.github.shimeoki.jwp.domain.values.ID;

public final class SourceRepository
        implements io.github.shimeoki.jwp.domain.repositories.SourceRepository {

    private final Database db;

    public SourceRepository(final Database db) {
        this.db = Objects.requireNonNull(db);
    }

    @Override
    public void save(final Source s) {
        db.addSource(Objects.requireNonNull(s));
    }

    @Override
    public void delete(final ID id) {
        db.removeSource(Objects.requireNonNull(id));
    }

    @Override
    public Optional<Source> findByID(final ID id) {
        return Optional.ofNullable(db.getSourceByID(Objects.requireNonNull(id)));
    }

    @Override
    public List<Source> findAll() {
        return db.getAllSources();
    }

    @Override
    public long count() {
        return db.getSourceCount();
    }
}
