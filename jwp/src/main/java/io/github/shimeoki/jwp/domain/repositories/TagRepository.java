package io.github.shimeoki.jwp.domain.repositories;

import java.util.Optional;

import io.github.shimeoki.jwp.domain.entities.Tag;
import io.github.shimeoki.jwp.domain.values.Name;

public interface TagRepository extends Repository<Tag> {

    Optional<Tag> findByName(Name n);
}
