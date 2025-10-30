package io.github.shimeoki.jwp.domain.entities;

import java.util.Date;
import java.util.Objects;

import io.github.shimeoki.jwp.domain.values.ID;
import io.github.shimeoki.jwp.domain.values.Name;

public final class Alias implements Cloneable {

    private final ID id;
    private final ID wallpaperID;
    private final Date createdAt;

    private Name name;
    private Date updatedAt;

    public Alias(
            final ID id,
            final ID wallpaperID,
            final Name n,
            final Date createdAt,
            final Date updatedAt) {

        this.id = id;
        this.wallpaperID = wallpaperID;
        this.name = n;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Alias(final ID wallpaperID, final Name n) {
        this.id = new ID();
        this.wallpaperID = Objects.requireNonNull(wallpaperID);
        this.createdAt = new Date();
        rename(n);
    }

    public void rename(final Name n) {
        this.name = Objects.requireNonNull(n);
        this.updatedAt = new Date();
        validate();
    }

    private void validate() {
        if (createdAt.after(updatedAt)) {
            throw new InvalidTimestampsException(createdAt, updatedAt);
        }
    }

    public ID id() {
        return id;
    }

    public ID wallpaperID() {
        return wallpaperID;
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
    public Alias clone() {
        return new Alias(id, wallpaperID, name, createdAt, updatedAt);
    }
}
