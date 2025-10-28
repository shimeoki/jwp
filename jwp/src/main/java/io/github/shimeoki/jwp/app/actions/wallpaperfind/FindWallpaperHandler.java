package io.github.shimeoki.jwp.app.actions.wallpaperfind;

import java.util.Objects;

import io.github.shimeoki.jwp.app.ApplicationException;
import io.github.shimeoki.jwp.app.Handler;
import io.github.shimeoki.jwp.app.NotFoundException;
import io.github.shimeoki.jwp.domain.values.Hash;

public final class FindWallpaperHandler
        implements Handler<FindWallpaperQuery, FindWallpaperResult> {

    private final FindWallpaperWorker worker;

    public FindWallpaperHandler(final FindWallpaperWorker w) {
        worker = Objects.requireNonNull(w);
    }

    @Override
    public FindWallpaperResult handle(final FindWallpaperQuery qry) {
        try (final var p = worker.work()) {
            final var h = Hash.fromString(qry.hash());

            final var w = p.wallpaperRepository().findByHash(h).orElseThrow(
                    () -> new NotFoundException(
                            "wallpaper", "hash", h.toString()));

            return new FindWallpaperResult(w.format().toString());
        } catch (final Exception e) {
            throw new ApplicationException("wallpaperfind", e);
        }
    }
}
