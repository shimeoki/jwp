package io.github.shimeoki.jwp.app.actions.wallpapercreate;

import java.util.Objects;

import io.github.shimeoki.jwp.app.AlreadyExistsException;
import io.github.shimeoki.jwp.app.ApplicationException;
import io.github.shimeoki.jwp.app.Handler;
import io.github.shimeoki.jwp.domain.entities.Wallpaper;
import io.github.shimeoki.jwp.domain.values.Format;

public final class CreateWallpaperHandler
        implements Handler<CreateWallpaperCommand, CreateWallpaperResult> {

    private final CreateWallpaperWorker worker;

    public CreateWallpaperHandler(final CreateWallpaperWorker w) {
        worker = Objects.requireNonNull(w);
    }

    @Override
    public CreateWallpaperResult handle(final CreateWallpaperCommand cmd) {
        try (final var p = worker.work()) {
            final var h = p.store().create(cmd.image());

            p.wallpaperRepository().findByHash(h).ifPresent((_) -> {
                throw new AlreadyExistsException(
                        "wallpaper", "hash", h.toString());
            });

            final var f = Format.fromExtension(cmd.format());
            final var w = new Wallpaper(f, h);

            p.wallpaperRepository().save(w);
            p.commit();

            return new CreateWallpaperResult(h.toString());
        } catch (final Exception e) {
            throw new ApplicationException("wallpapercreate", e);
        }
    }
}
