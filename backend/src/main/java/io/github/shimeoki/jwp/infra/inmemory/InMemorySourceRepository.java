package io.github.shimeoki.jwp.infra.inmemory;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import io.github.shimeoki.jwp.domain.entities.Source;
import io.github.shimeoki.jwp.domain.repositories.SourceRepository;
import io.github.shimeoki.jwp.domain.values.ID;

public final class InMemorySourceRepository implements SourceRepository {

    private final InMemoryDatabase db;

    public InMemorySourceRepository() {
        db = InMemoryDatabase.getInstance();
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
    public Stream<Source> findAll() {
        return db.getAllSources().stream();
    }

    @Override
    public long count() {
        return db.getSourceCount();
    }
}
