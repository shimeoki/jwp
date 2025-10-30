package io.github.shimeoki.jwp.domain.repositories;

import java.util.List;
import java.util.Optional;

import io.github.shimeoki.jwp.domain.values.ID;

public interface Repository<E> {

    void save(E entity);

    void delete(ID id);

    Optional<E> findByID(ID id);

    List<E> findAll();

    long count();
}
