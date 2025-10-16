package io.github.shimeoki.jwp.infrastructure.inmemory;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import io.github.shimeoki.jwp.domain.entities.Tag;
import io.github.shimeoki.jwp.domain.repositories.TagRepository;
import io.github.shimeoki.jwp.domain.values.ID;
import io.github.shimeoki.jwp.domain.values.Name;

public final class InMemoryTagRepository implements TagRepository {

    private final InMemoryDatabase db;

    public InMemoryTagRepository(final InMemoryDatabase db) {
        this.db = Objects.requireNonNull(db);
    }

    @Override
    public void save(final Tag t) {
        this.db.addTag(Objects.requireNonNull(t));
    }

    @Override
    public void delete(final ID id) {
        this.db.removeTag(Objects.requireNonNull(id));
    }

    @Override
    public Optional<Tag> findByID(final ID id) {
        return Optional.ofNullable(
                this.db.getTagByID(Objects.requireNonNull(id)));
    }

    @Override
    public Stream<Tag> findAll() {
        return this.db.getAllTags().stream();
    }

    @Override
    public long count() {
        return this.db.getTagCount();
    }

    @Override
    public Optional<Tag> findByName(final Name n) {
        return Optional.ofNullable(
                this.db.getTagByName(Objects.requireNonNull(n)));
    }

}
