package io.github.shimeoki.jwp.domain.entities;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import io.github.shimeoki.jwp.domain.values.Format;
import io.github.shimeoki.jwp.domain.values.Hash;
import io.github.shimeoki.jwp.domain.values.ID;

public class Wallpaper {

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
        this.id = new ID();
        this.createdAt = new Date();
        this.sources = new HashMap<ID, Source>();
        this.tags = new HashMap<ID, Tag>();

        this.setHash(h);
        this.setFormat(f);
        this.touch();
        this.validate();
    }

    public void updateHash(final Hash h) {
        this.setHash(h);
        this.touch();
        this.validate();
    }

    public void updateFormat(final Format f) {
        this.setFormat(f);
        this.touch();
        this.validate();
    }

    private void setHash(final Hash h) {
        this.hash = Objects.requireNonNull(h);
    }

    private void setFormat(final Format f) {
        this.format = Objects.requireNonNull(f);
    }

    private void touch() {
        this.updatedAt = new Date();
    }

    private void validate() {
        if (this.createdAt.after(this.updatedAt)) {
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
        return this.sources.values();
    }

    public boolean addSource(final Source s) {
        return this.sources.putIfAbsent(s.getID(), s) == null;
    }

    public boolean removeSource(final ID id) {
        return this.sources.remove(id) != null;
    }

    public Source getSource(final ID id) {
        return this.sources.get(id);
    }

    public Collection<Tag> getTags() {
        return this.tags.values();
    }

    public boolean addTag(final Tag t) {
        return this.tags.putIfAbsent(t.getID(), t) == null;
    }

    public boolean removeTag(final ID id) {
        return this.tags.remove(id) != null;
    }

    public Tag getTag(final ID id) {
        return this.tags.get(id);
    }
}
