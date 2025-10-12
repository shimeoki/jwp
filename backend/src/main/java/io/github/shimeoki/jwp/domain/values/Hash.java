package io.github.shimeoki.jwp.domain.values;

import java.util.Objects;

public record Hash(String value) {
    public Hash {
        if (Objects.requireNonNull(value).isEmpty()) {
            throw new IllegalArgumentException("value is empty");
        }
    }
}
