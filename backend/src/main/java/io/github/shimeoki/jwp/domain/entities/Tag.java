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
        this.id = new ID();
        this.createdAt = new Date();
        this.rename(n);
    }

    public void rename(final Name n) {
        this.name = Objects.requireNonNull(n);
        this.updatedAt = new Date();
        this.validate();
    }

    private void validate() {
        if (this.createdAt.after(this.updatedAt)) {
            throw new IllegalStateException("created is after updated");
        }
    }

    public ID getID() {
        return id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Name getName() {
        return name;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public Tag clone() {
        return new Tag(
                this.id,
                this.name,
                this.createdAt,
                this.updatedAt);
    }
}
