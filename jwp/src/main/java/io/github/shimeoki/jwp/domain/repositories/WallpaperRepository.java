package io.github.shimeoki.jwp.domain.repositories;

import java.util.List;
import java.util.Optional;

import io.github.shimeoki.jwp.domain.entities.Wallpaper;
import io.github.shimeoki.jwp.domain.values.Hash;
import io.github.shimeoki.jwp.domain.values.ID;

public interface WallpaperRepository extends Repository<Wallpaper> {

    Optional<Wallpaper> findByHash(Hash h);

    List<Wallpaper> findByTagID(ID id);
}
