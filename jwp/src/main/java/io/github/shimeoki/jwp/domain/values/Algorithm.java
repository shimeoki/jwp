package io.github.shimeoki.jwp.domain.values;

public enum Algorithm {

    SHA256("sha256"),
    MD5("md5");

    private final String value;

    private Algorithm(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
