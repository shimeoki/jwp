package io.github.shimeoki.jwp.domain.repositories;

import java.util.List;

import io.github.shimeoki.jwp.domain.entities.Alias;
import io.github.shimeoki.jwp.domain.values.ID;

public interface AliasRepository extends Repository<Alias> {

    List<Alias> findByWallpaperID(ID id);
}
