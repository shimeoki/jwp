package io.github.shimeoki.jwp.config;

import io.github.shimeoki.jwp.domain.repositories.TagRepository;
import io.github.shimeoki.jwp.domain.repositories.WallpaperRepository;

public record Repositories(
        TagRepository tags,
        WallpaperRepository wallpapers) {
}
