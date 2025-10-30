package io.github.shimeoki.jwp.app.providers;

import io.github.shimeoki.jwp.app.Provider;
import io.github.shimeoki.jwp.domain.repositories.WallpaperRepository;

public interface WallpaperProvider extends Provider {

    WallpaperRepository wallpaperRepository();
}
