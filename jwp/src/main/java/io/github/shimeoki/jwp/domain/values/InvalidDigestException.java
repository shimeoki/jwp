package io.github.shimeoki.jwp.domain.values;

public class InvalidDigestException extends RuntimeException {

    public InvalidDigestException(
            final String algorithm,
            final String digest,
            final String regex) {

        super(String.format("expected %s for %s, got %s",
                regex, algorithm, digest));
    }
}
