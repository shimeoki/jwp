package io.github.shimeoki.jwp.infra.store;

import java.io.IOException;
import java.io.InputStream;

import io.github.shimeoki.jwp.domain.values.Hash;

@FunctionalInterface
public interface Hasher {

    Hash compute(InputStream img) throws IOException;
}
