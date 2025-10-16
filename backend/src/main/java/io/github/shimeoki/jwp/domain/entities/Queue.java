package io.github.shimeoki.jwp.domain.entities;

import java.util.Date;
import java.util.Objects;

import io.github.shimeoki.jwp.domain.values.ID;
import io.github.shimeoki.jwp.domain.values.Status;

public final class Queue implements Cloneable {

    private final ID id;
    private final ID wallpaperID;
    private final Date createdAt;

    private Status status;
    private Integer priority;
    private Date updatedAt;

    public Queue(
            final ID id,
            final ID wallpaperID,
            final Status s,
            final Integer priority,
            final Date createdAt,
            final Date updatedAt) {

        this.id = id;
        this.wallpaperID = wallpaperID;
        this.status = s;
        this.priority = priority;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Queue(final ID wallpaperID, final Status s, final Integer priority) {
        this.id = new ID();
        this.wallpaperID = Objects.requireNonNull(wallpaperID);
        this.createdAt = new Date();

        setStatus(s);
        setPriority(priority);
        touch();
        validate();
    }

    public void updateStatus(final Status s) {
        setStatus(s);
        touch();
        validate();
    }

    public void updatePriority(final Integer priority) {
        setPriority(priority);
        touch();
        validate();
    }

    private void setStatus(final Status s) {
        this.status = Objects.requireNonNull(s);
    }

    private void setPriority(final Integer priority) {
        this.priority = Objects.requireNonNull(priority);
    }

    private void touch() {
        updatedAt = new Date();
    }

    private void validate() {
        if (createdAt.after(updatedAt)) {
            throw new IllegalStateException("created is after updated");
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

    public Status status() {
        return status;
    }

    public Integer priority() {
        return priority;
    }

    public Date updatedAt() {
        return updatedAt;
    }

    @Override
    public Queue clone() {
        return new Queue(id, wallpaperID, status, priority, createdAt, updatedAt);
    }
}
