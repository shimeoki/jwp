package io.github.shimeoki.jwp.domain.values;

import java.util.Objects;

public record Name(String value) {
    public Name {
        if (Objects.requireNonNull(value).isEmpty()) {
            throw new EmptyNameException();
        }
    }

    @Override
    public final String toString() {
        return value;
    }
}
