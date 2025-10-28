package io.github.shimeoki.jwp.config;

import io.github.shimeoki.jwp.domain.repositories.AliasRepository;
import io.github.shimeoki.jwp.domain.repositories.SourceRepository;
import io.github.shimeoki.jwp.domain.repositories.Store;
import io.github.shimeoki.jwp.domain.repositories.TagRepository;
import io.github.shimeoki.jwp.domain.repositories.WallpaperRepository;

public record Storage(
        TagRepository tags,
        WallpaperRepository wallpapers,
        SourceRepository sources,
        AliasRepository aliases,
        Store store) {
}
