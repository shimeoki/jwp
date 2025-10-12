package io.github.shimeoki.jwp.domain.values;

import java.util.UUID;

public class ID {

    private String value;

    private ID(String value) {
        this.value = value;
    }

    public ID() {
        this(UUID.randomUUID().toString());
    }

    @Override
    public String toString() {
        return this.value;
    }

    public static ID fromString(String value) {
        return new ID(UUID.fromString(value).toString());
    }
}
