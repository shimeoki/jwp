package io.github.shimeoki.jwp.domain.values;

public enum Status {

    QUEUED("queued"),
    USED("used"),
    SKIPPED("skipped");

    private final String value;

    private Status(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
