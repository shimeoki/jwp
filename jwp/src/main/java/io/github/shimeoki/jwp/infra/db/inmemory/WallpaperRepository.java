package io.github.shimeoki.jwp.infra.db.inmemory;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import io.github.shimeoki.jwp.domain.entities.Wallpaper;
import io.github.shimeoki.jwp.domain.values.Hash;
import io.github.shimeoki.jwp.domain.values.ID;

public final class WallpaperRepository
        implements io.github.shimeoki.jwp.domain.repositories.WallpaperRepository {

    private final Database db;

    public WallpaperRepository(final Database db) {
        this.db = Objects.requireNonNull(db);
    }

    @Override
    public void save(final Wallpaper w) {
        db.addWallpaper(Objects.requireNonNull(w));
    }

    @Override
    public void delete(final ID id) {
        db.removeWallpaper(Objects.requireNonNull(id));
    }

    @Override
    public Optional<Wallpaper> findByID(final ID id) {
        return Optional.ofNullable(
                db.getWallpaperByID(Objects.requireNonNull(id)));
    }

    @Override
    public Stream<Wallpaper> findAll() {
        return db.getAllWallpapers().stream();
    }

    @Override
    public long count() {
        return db.getWallpaperCount();
    }

    @Override
    public Optional<Wallpaper> findByHash(final Hash h) {
        return Optional.ofNullable(
                db.getWallpaperByHash(Objects.requireNonNull(h)));
    }

    @Override
    public Stream<Wallpaper> findByTagID(final ID id) {
        return db.getWallpapersByTagID(id).stream();
    }
}
