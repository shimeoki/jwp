package io.github.shimeoki.jwp.infra.db.inmemory;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import io.github.shimeoki.jwp.domain.entities.Alias;
import io.github.shimeoki.jwp.domain.values.ID;

public final class AliasRepository
        implements io.github.shimeoki.jwp.domain.repositories.AliasRepository {

    private final Database db;

    public AliasRepository(final Database db) {
        this.db = Objects.requireNonNull(db);
    }

    @Override
    public void save(final Alias a) {
        db.addAlias(Objects.requireNonNull(a));
    }

    @Override
    public void delete(final ID id) {
        db.removeAlias(Objects.requireNonNull(id));
    }

    @Override
    public Optional<Alias> findByID(final ID id) {
        return Optional.ofNullable(
                db.getAliasByID(Objects.requireNonNull(id)));
    }

    @Override
    public Stream<Alias> findAll() {
        return db.getAllAliases().stream();
    }

    @Override
    public long count() {
        return db.getAliasCount();
    }

    @Override
    public Stream<Alias> findByWallpaperID(final ID id) {
        return db.getAliasesByWallpaperID(Objects.requireNonNull(id)).stream();
    }
}
