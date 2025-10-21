package io.github.shimeoki.jwp.domain.values;

import java.io.Serializable;
import java.util.UUID;

public final class ID implements Serializable, Comparable<ID> {

    private final UUID uuid;

    private ID(final UUID uuid) {
        this.uuid = uuid;
    }

    public ID() {
        this(UUID.randomUUID());
    }

    @Override
    public String toString() {
        return uuid.toString();
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof ID id)) {
            return false;
        }

        return uuid.equals(id.uuid);
    }

    @Override
    public int compareTo(final ID other) {
        return uuid.compareTo(other.uuid);
    }

    public static ID fromString(final String value) {
        return new ID(UUID.fromString(value));
    }
}
