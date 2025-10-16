package io.github.shimeoki.jwp.domain.entities;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import io.github.shimeoki.jwp.domain.values.Format;
import io.github.shimeoki.jwp.domain.values.Hash;
import io.github.shimeoki.jwp.domain.values.ID;

public final class Wallpaper implements Cloneable {

    private final ID id;
    private final Date createdAt;
    private final Map<ID, Source> sources;
    private final Map<ID, Tag> tags;

    private Format format;
    private Hash hash;
    private Date updatedAt;

    public Wallpaper(
            final ID id,
            final Format f,
            final Hash h,
            final Map<ID, Source> sources,
            final Map<ID, Tag> tags,
            final Date createdAt,
            final Date updatedAt) {

        this.id = id;
        this.format = f;
        this.hash = h;
        this.sources = sources;
        this.tags = tags;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Wallpaper(final Format f, final Hash h) {
        id = new ID();
        createdAt = new Date();
        sources = new HashMap<ID, Source>();
        tags = new HashMap<ID, Tag>();

        setHash(h);
        setFormat(f);
        touch();
        validate();
    }

    public void updateHash(final Hash h) {
        setHash(h);
        touch();
        validate();
    }

    public void updateFormat(final Format f) {
        setFormat(f);
        touch();
        validate();
    }

    private void setHash(final Hash h) {
        hash = Objects.requireNonNull(h);
    }

    private void setFormat(final Format f) {
        format = Objects.requireNonNull(f);
    }

    private void touch() {
        updatedAt = new Date();
    }

    private void validate() {
        if (createdAt.after(updatedAt)) {
            throw new IllegalStateException("created is after updated");
        }
    }

    public ID getID() {
        return id;
    }

    public Format getFormat() {
        return format;
    }

    public Hash getHash() {
        return hash;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public Collection<Source> getSources() {
        return sources.values();
    }

    public boolean addSource(final Source s) {
        return sources.putIfAbsent(s.getID(), s) == null;
    }

    public boolean removeSource(final ID id) {
        return sources.remove(id) != null;
    }

    public Source getSource(final ID id) {
        return sources.get(id);
    }

    public Collection<Tag> getTags() {
        return tags.values();
    }

    public boolean addTag(final Tag t) {
        return tags.putIfAbsent(t.id(), t) == null;
    }

    public boolean removeTag(final ID id) {
        return tags.remove(id) != null;
    }

    public Tag getTag(final ID id) {
        return tags.get(id);
    }

    @Override
    public Wallpaper clone() {
        final var tags = new HashMap<ID, Tag>();
        for (final var tag : getTags()) {
            tags.put(tag.id(), tag.clone());
        }

        final var sources = new HashMap<ID, Source>();
        for (final var source : getSources()) {
            sources.put(source.getID(), source.clone());
        }

        return new Wallpaper(id, format, hash, sources, tags, createdAt, updatedAt);
    }
}
