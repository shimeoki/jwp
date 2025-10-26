package io.github.shimeoki.jwp.app.actions.wallpapershow;

import java.util.Objects;

import io.github.shimeoki.jwp.app.Handler;
import io.github.shimeoki.jwp.domain.values.Hash;

public final class ShowWallpaperHandler
        implements Handler<ShowWallpaperQuery, ShowWallpaperResult> {

    private final ShowWallpaperWorker worker;

    public ShowWallpaperHandler(final ShowWallpaperWorker w) {
        worker = Objects.requireNonNull(w);
    }

    @Override
    public ShowWallpaperResult handle(final ShowWallpaperQuery qry) {
        try (final var p = worker.work()) {
            final var h = Hash.fromString(qry.hash());

            final var w = p.wallpaperRepository().findByHash(h).orElseThrow(
                    () -> new IllegalArgumentException("wallpaper not found"));

            final var img = p.store().get(h);

            return new ShowWallpaperResult(img, w.format().toString());
        } catch (final Exception e) {
            // TODO: handle
            return null;
        }
    }
}
