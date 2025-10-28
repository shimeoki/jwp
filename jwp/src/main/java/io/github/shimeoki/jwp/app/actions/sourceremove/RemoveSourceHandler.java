package io.github.shimeoki.jwp.app.actions.sourceremove;

import java.util.Objects;

import io.github.shimeoki.jwp.app.ApplicationException;
import io.github.shimeoki.jwp.app.Handler;
import io.github.shimeoki.jwp.app.InvalidRelationException;
import io.github.shimeoki.jwp.app.NotFoundException;
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
                    () -> new NotFoundException(
                            "wallpaper", "hash", h.toString()));

            final var id = ID.fromString(cmd.sourceID());

            final var s = p.sourceRepository().findByID(id).orElseThrow(
                    () -> new NotFoundException(
                            "source", "id", id.toString()));

            if (w.getSource(id) == null) {
                throw new InvalidRelationException("source", false);
            }

            w.removeSource(s.id());

            p.wallpaperRepository().save(w);
            p.commit();

            return new RemoveSourceResult();
        } catch (final Exception e) {
            throw new ApplicationException("sourceremove", e);
        }
    }
}
