package io.github.shimeoki.jwp.infra.store;

import java.security.MessageDigest;

import io.github.shimeoki.jwp.domain.values.Algorithm;
import io.github.shimeoki.jwp.domain.values.Hash;

public final class Hashers {

    private static final String MD5 = "MD5";
    // private static final String SHA1 = "SHA-1"; // not used yet
    private static final String SHA256 = "SHA-256";

    private static final int SIZE = 8192;

    private static String convert(final Algorithm a) {
        switch (a) {
            case SHA256:
                return SHA256;
            case MD5:
                return MD5;
            default:
                throw new IllegalArgumentException("unsupported algorithm");
        }
    }

    public static Hasher fromAlgorithm(final Algorithm a) {
        return (img) -> {
            try {
                final var md = MessageDigest.getInstance(convert(a));
                final var buf = new byte[SIZE];

                int read;
                while ((read = img.read(buf)) != -1) {
                    md.update(buf, 0, read);
                }

                return new Hash(a, md.digest().toString());
            } catch (final Exception e) {
                // TODO: handle
                return null;
            }
        };
    }
}
