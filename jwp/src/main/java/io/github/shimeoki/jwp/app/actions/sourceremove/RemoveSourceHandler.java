package io.github.shimeoki.jwp.app.actions.sourceremove;

import java.util.Objects;

import io.github.shimeoki.jwp.app.Handler;
import io.github.shimeoki.jwp.domain.values.Hash;
import io.github.shimeoki.jwp.domain.values.ID;

public final class RemoveSourceHandler
        implements Handler<RemoveSourceCommand, RemoveSourceResult> {

    private final RemoveSourceWorker worker;

    public RemoveSourceHandler(final RemoveSourceWorker w) {
        worker = Objects.requireNonNull(w);
    }

    @Override
    public RemoveSourceResult handle(final RemoveSourceCommand cmd) {
        try (final var p = worker.work()) {
            final var h = Hash.fromString(cmd.wallpaperHash());

            final var w = p.wallpaperRepository().findByHash(h).orElseThrow(
                    () -> new IllegalArgumentException("wallpaper not found"));

            final var id = ID.fromString(cmd.sourceID());

            final var s = p.sourceRepository().findByID(id).orElseThrow(
                    () -> new IllegalArgumentException("source not found"));

            if (w.getSource(id) == null) {
                throw new IllegalArgumentException("source not attached");
            }

            w.removeSource(s.id());

            p.wallpaperRepository().save(w);
            p.commit();

            return new RemoveSourceResult();
        } catch (final Exception e) {
            // TODO: handle
            return null;
        }
    }
}
