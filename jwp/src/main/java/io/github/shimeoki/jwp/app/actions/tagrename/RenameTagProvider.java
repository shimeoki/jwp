package io.github.shimeoki.jwp.app.actions.tagrename;

import io.github.shimeoki.jwp.app.Provider;
import io.github.shimeoki.jwp.domain.repositories.TagRepository;
import io.github.shimeoki.jwp.domain.repositories.WallpaperRepository;

public interface RenameTagProvider extends Provider {

    WallpaperRepository wallpaperRepository();

    TagRepository tagRepository();
}
