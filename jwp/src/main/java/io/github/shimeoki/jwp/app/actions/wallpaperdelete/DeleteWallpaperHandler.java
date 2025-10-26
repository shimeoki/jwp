package io.github.shimeoki.jwp.app.actions.wallpaperdelete;

import java.util.Objects;

import io.github.shimeoki.jwp.app.Handler;
import io.github.shimeoki.jwp.domain.values.Hash;

public final class DeleteWallpaperHandler
        implements Handler<DeleteWallpaperCommand, DeleteWallpaperResult> {

    private final DeleteWallpaperWorker worker;

    public DeleteWallpaperHandler(final DeleteWallpaperWorker w) {
        worker = Objects.requireNonNull(w);
    }

    @Override
    public DeleteWallpaperResult handle(final DeleteWallpaperCommand cmd) {
        try (final var p = worker.work()) {
            final var h = Hash.fromString(cmd.hash());

            final var w = p.wallpaperRepository().findByHash(h).orElseThrow(
                    () -> new IllegalArgumentException("wallpaper not found"));

            p.wallpaperRepository().delete(w.id());
            p.store().delete(h);
            p.commit();

            return new DeleteWallpaperResult();
        } catch (final Exception e) {
            // TODO: handle
            return null;
        }
    }
}
