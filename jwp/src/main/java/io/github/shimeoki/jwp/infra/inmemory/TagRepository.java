package io.github.shimeoki.jwp.infra.inmemory;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import io.github.shimeoki.jwp.domain.entities.Tag;
import io.github.shimeoki.jwp.domain.values.ID;
import io.github.shimeoki.jwp.domain.values.Name;

public final class TagRepository
        implements io.github.shimeoki.jwp.domain.repositories.TagRepository {

    private final InMemoryDatabase db;

    public TagRepository() {
        db = InMemoryDatabase.open();
    }

    @Override
    public void save(final Tag t) {
        db.addTag(Objects.requireNonNull(t));
    }

    @Override
    public void delete(final ID id) {
        db.removeTag(Objects.requireNonNull(id));
    }

    @Override
    public Optional<Tag> findByID(final ID id) {
        return Optional.ofNullable(db.getTagByID(Objects.requireNonNull(id)));
    }

    @Override
    public Stream<Tag> findAll() {
        return db.getAllTags().stream();
    }

    @Override
    public long count() {
        return db.getTagCount();
    }

    @Override
    public Optional<Tag> findByName(final Name n) {
        return Optional.ofNullable(db.getTagByName(Objects.requireNonNull(n)));
    }
}
