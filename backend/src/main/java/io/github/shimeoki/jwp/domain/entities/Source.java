package io.github.shimeoki.jwp.domain.entities;

import java.util.Date;
import java.util.Objects;

import io.github.shimeoki.jwp.domain.values.ID;
import io.github.shimeoki.jwp.domain.values.Name;

public final class Source implements Cloneable {

    private final ID id;
    private final Date createdAt;

    private Name name;
    private String link;
    private Date updatedAt;

    public Source(
            final ID id,
            final Name n,
            final String link,
            final Date createdAt,
            final Date updatedAt) {

        this.id = id;
        this.name = n;
        this.link = link;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Source(final Name n, final String link) {
        id = new ID();
        createdAt = new Date();

        setName(n);
        setLink(link);
        touch();
        validate();
    }

    public void rename(final Name n) {
        setName(n);
        touch();
        validate();
    }

    public void updateLink(final String link) {
        setLink(link);
        touch();
        validate();
    }

    private void setName(final Name n) {
        this.name = Objects.requireNonNull(n);
    }

    private void setLink(final String link) {
        this.link = link;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public Name getName() {
        return name;
    }

    public String getLink() {
        return link;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public Source clone() {
        return new Source(id, name, link, createdAt, updatedAt);
    }
}
