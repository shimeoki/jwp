package io.github.shimeoki.jwp.domain.repositories;

import java.io.IOException;
import java.io.InputStream;

import io.github.shimeoki.jwp.domain.values.Hash;

public interface Store {

    InputStream get(Hash h) throws IOException;

    Hash create(InputStream img) throws IOException;

    void delete(Hash h) throws IOException;
}
