package io.github.shimeoki.jwp.domain.values;

public enum Algorithm {

    SHA256("sha256"),
    MD5("md5");

    private final String value;

    private Algorithm(final String value) {
        this.value = value;
    }

    public static Algorithm fromString(final String algorithm) {
        switch (algorithm.toLowerCase()) {
            case "sha256":
                return SHA256;
            case "md5":
                return MD5;
            default:
                throw new InvalidAlgorithmException(algorithm);
        }
    }

    @Override
    public String toString() {
        return value;
    }
}
