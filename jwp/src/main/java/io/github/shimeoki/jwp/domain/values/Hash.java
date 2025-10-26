package io.github.shimeoki.jwp.domain.values;

import java.io.Serializable;
import java.util.Objects;

public final class Hash implements Serializable, Comparable<Hash> {

    private final Algorithm algorithm;
    private final String digest;

    private final String value;

    public Hash(final Algorithm a, final String digest) {
        this.algorithm = Objects.requireNonNull(a);
        this.digest = Objects.requireNonNull(digest).toLowerCase();
        validate();

        this.value = String.format("%s-%s", a.toString(), digest);
    }

    public Algorithm algorithm() {
        return algorithm;
    }

    public String digest() {
        return digest;
    }

    private void validate() {
        switch (algorithm) {
            case SHA256:
                validateSHA256();
                break;
            case MD5:
                validateMD5();
                break;
        }
    }

    private void validateSHA256() {
        if (digest.length() != 64) {
            throw new IllegalArgumentException("invalid sha256 digest");
        }

        if (!digest.matches("^[a-fA-F0-9]{64}$")) {
            throw new IllegalArgumentException("invalid sha256 digest");
        }
    }

    private void validateMD5() {
        if (digest.length() != 32) {
            throw new IllegalArgumentException("invalid md5 digest");
        }

        if (!digest.matches("^[a-fA-F0-9]{32}$")) {
            throw new IllegalArgumentException("invalid md5 digest");
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof Hash h)) {
            return false;
        }

        return toString().equals(h.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public int compareTo(final Hash other) {
        final var algo1 = algorithm().toString();
        final var algo2 = other.algorithm().toString();

        final var algos = algo1.compareTo(algo2);
        if (algos != 0) {
            return algos;
        }

        return digest().compareTo(other.digest());
    }

    public static Hash fromString(final String value) {
        final var parts = Objects.requireNonNull(value).split("-");
        if (parts.length != 2) {
            throw new IllegalArgumentException("invalid hash value");
        }

        return new Hash(Algorithm.fromString(parts[0]), parts[1]);
    }

    @Override
    public String toString() {
        return value;
    }
}
