package io.github.shimeoki.jwp.app.actions.sourceadd;

import java.util.Objects;

import io.github.shimeoki.jwp.app.ApplicationException;
import io.github.shimeoki.jwp.app.Handler;
import io.github.shimeoki.jwp.app.InvalidRelationException;
import io.github.shimeoki.jwp.app.NotFoundException;
import io.github.shimeoki.jwp.domain.values.Hash;
import io.github.shimeoki.jwp.domain.values.ID;

public final class AddSourceHandler
        implements Handler<AddSourceCommand, AddSourceResult> {

    private final AddSourceWorker worker;

    public AddSourceHandler(final AddSourceWorker w) {
        worker = Objects.requireNonNull(w);
    }

    @Override
    public AddSourceResult handle(final AddSourceCommand cmd) {
        try (final var p = worker.work()) {
            final var h = Hash.fromString(cmd.wallpaperHash());

            final var w = p.wallpaperRepository().findByHash(h).orElseThrow(
                    () -> new NotFoundException(
                            "wallpaper", "hash", h.toString()));

            final var id = ID.fromString(cmd.sourceID());

            final var s = p.sourceRepository().findByID(id).orElseThrow(
                    () -> new NotFoundException(
                            "source", "id", id.toString()));

            if (w.getSource(id) != null) {
                throw new InvalidRelationException("source", true);
            }

            w.addSource(s);

            p.wallpaperRepository().save(w);
            p.commit();

            return new AddSourceResult();
        } catch (final Exception e) {
            throw new ApplicationException("sourceadd", e);
        }
    }
}
