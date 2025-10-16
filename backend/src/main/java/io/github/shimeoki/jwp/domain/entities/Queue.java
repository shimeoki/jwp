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

        this.setStatus(s);
        this.setPriority(priority);
        this.touch();
        this.validate();
    }

    public void updateStatus(final Status s) {
        this.setStatus(s);
        this.touch();
        this.validate();
    }

    public void updatePriority(final int priority) {
        this.setPriority(priority);
        this.touch();
        this.validate();
    }

    private void setStatus(final Status s) {
        this.status = Objects.requireNonNull(s);
    }

    private void setPriority(final Integer priority) {
        this.priority = Objects.requireNonNull(priority);
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

    public ID getWallpaperID() {
        return wallpaperID;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Status getStatus() {
        return status;
    }

    public Integer getPriority() {
        return priority;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public Queue clone() {
        return new Queue(
                this.id,
                this.wallpaperID,
                this.status,
                this.priority,
                this.createdAt,
                this.updatedAt);
    }
}
