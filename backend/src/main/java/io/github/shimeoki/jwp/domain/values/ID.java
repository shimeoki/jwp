package io.github.shimeoki.jwp.domain.values;

import java.io.Serializable;
import java.util.UUID;

public class ID implements Serializable, Comparable<ID> {

    private UUID uuid;

    private ID(UUID uuid) {
        this.uuid = uuid;
    }

    public ID() {
        this(UUID.randomUUID());
    }

    @Override
    public String toString() {
        return this.uuid.toString();
    }

    @Override
    public int hashCode() {
        return this.uuid.hashCode();
    }

    @Override
    public int compareTo(ID other) {
        return this.uuid.compareTo(other.uuid);
    }

    public static ID fromString(String value) {
        return new ID(UUID.fromString(value));
    }
}
