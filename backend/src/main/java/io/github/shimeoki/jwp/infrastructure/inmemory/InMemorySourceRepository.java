package io.github.shimeoki.jwp.infrastructure.inmemory;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import io.github.shimeoki.jwp.domain.entities.Source;
import io.github.shimeoki.jwp.domain.repositories.SourceRepository;
import io.github.shimeoki.jwp.domain.values.ID;

public final class InMemorySourceRepository implements SourceRepository {

    private final InMemoryDatabase db;

    public InMemorySourceRepository() {
        this.db = InMemoryDatabase.getInstance();
    }

    @Override
    public void save(final Source s) {
        this.db.addSource(Objects.requireNonNull(s));
    }

    @Override
    public void delete(final ID id) {
        this.db.removeSource(id);
    }

    @Override
    public Optional<Source> findByID(final ID id) {
        return Optional.ofNullable(this.db.getSourceByID(id));
    }

    @Override
    public Stream<Source> findAll() {
        return this.db.getAllSources().stream();
    }

    @Override
    public long count() {
        return this.db.getSourceCount();
    }

}
