package io.github.shimeoki.jwp.domain.values;

public class InvalidAlgorithmException extends RuntimeException {

    public InvalidAlgorithmException(final String algorithm) {
        super(String.format("invalid or unsupported algorithm: %s", algorithm));
    }
}
