package io.github.shimeoki.jwp.domain.repositories;

import java.io.InputStream;

import io.github.shimeoki.jwp.domain.values.Hash;

public interface Store {

    InputStream get(Hash h);

    Hash create(InputStream img);

    void delete(Hash h);
}
