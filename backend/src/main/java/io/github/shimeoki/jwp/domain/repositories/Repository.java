package io.github.shimeoki.jwp.domain.repositories;

import java.util.Optional;
import java.util.stream.Stream;

import io.github.shimeoki.jwp.domain.values.ID;

public interface Repository<E> {

    void save(E entity);

    void delete(ID id);

    Optional<E> findByID(ID id);

    Stream<E> findAll();

    long count();
}
