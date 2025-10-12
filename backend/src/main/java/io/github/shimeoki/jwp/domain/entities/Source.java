package io.github.shimeoki.jwp.domain.entities;

import java.util.Date;
import java.util.Objects;

import io.github.shimeoki.jwp.domain.values.ID;
import io.github.shimeoki.jwp.domain.values.Name;

public class Source {

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
        this.id = new ID();
        this.createdAt = new Date();

        this.setName(n);
        this.setLink(link);
        this.touch();
        this.validate();
    }

    public void rename(final Name n) {
        this.setName(n);
        this.touch();
        this.validate();
    }

    public void updateLink(final String link) {
        this.setLink(link);
        this.touch();
        this.validate();
    }

    private void setName(final Name n) {
        this.name = Objects.requireNonNull(n);
    }

    private void setLink(final String link) {
        this.link = link;
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
}
