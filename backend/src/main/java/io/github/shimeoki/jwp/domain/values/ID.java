package io.github.shimeoki.jwp.domain.values;

import java.util.UUID;

public class ID {

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

    public static ID fromString(String value) {
        return new ID(UUID.fromString(value));
    }
}
