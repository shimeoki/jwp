package io.github.shimeoki.jwp.domain.repositories;

import java.util.stream.Stream;

import io.github.shimeoki.jwp.domain.entities.Alias;
import io.github.shimeoki.jwp.domain.values.ID;

public interface AliasRepository extends Repository<Alias> {

    // TODO: maybe change to collection
    Stream<Alias> findByWallpaperID(ID id);
}
