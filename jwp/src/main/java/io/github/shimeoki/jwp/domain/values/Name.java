package io.github.shimeoki.jwp.domain.values;

import java.util.Objects;

public record Name(String value) {
    public Name {
        if (Objects.requireNonNull(value).isEmpty()) {
            throw new IllegalArgumentException("value is empty");
        }
    }

    @Override
    public final String toString() {
        return value;
    }
}
