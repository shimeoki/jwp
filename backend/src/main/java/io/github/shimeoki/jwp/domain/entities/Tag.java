package io.github.shimeoki.jwp.domain.entities;

import java.util.Date;
import java.util.Objects;

import io.github.shimeoki.jwp.domain.values.ID;
import io.github.shimeoki.jwp.domain.values.Name;

public final class Tag implements Cloneable {

    private final ID id;
    private final Date createdAt;

    private Name name;
    private Date updatedAt;

    public Tag(
            final ID id,
            final Name n,
            final Date createdAt,
            final Date updatedAt) {

        this.id = id;
        this.name = n;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Tag(final Name n) {
        id = new ID();
        createdAt = new Date();
        rename(n);
    }

    public void rename(final Name n) {
        name = Objects.requireNonNull(n);
        updatedAt = new Date();
        validate();
    }

    private void validate() {
        if (createdAt.after(updatedAt)) {
            throw new IllegalStateException("created is after updated");
        }
    }

    public ID id() {
        return id;
    }

    public Date createdAt() {
        return createdAt;
    }

    public Name name() {
        return name;
    }

    public Date updatedAt() {
        return updatedAt;
    }

    @Override
    public Tag clone() {
        return new Tag(id, name, createdAt, updatedAt);
    }
}
