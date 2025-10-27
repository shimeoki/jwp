package io.github.shimeoki.jwp.app.actions.sourceadd;

import java.util.Objects;

import io.github.shimeoki.jwp.app.Handler;
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
                    () -> new IllegalArgumentException("wallpaper not found"));

            final var id = ID.fromString(cmd.sourceID());

            final var s = p.sourceRepository().findByID(id).orElseThrow(
                    () -> new IllegalArgumentException("source not found"));

            if (w.getSource(id) != null) {
                throw new IllegalArgumentException("source already attached");
            }

            w.addSource(s);

            p.wallpaperRepository().save(w);
            p.commit();

            return new AddSourceResult();
        } catch (final Exception e) {
            // TODO: handle
            return null;
        }
    }
}
