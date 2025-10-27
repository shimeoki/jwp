package io.github.shimeoki.jwp.app.actions.tagremove;

import java.util.Objects;

import io.github.shimeoki.jwp.app.Handler;
import io.github.shimeoki.jwp.domain.values.Hash;
import io.github.shimeoki.jwp.domain.values.Name;

public final class RemoveTagHandler
        implements Handler<RemoveTagCommand, RemoveTagResult> {

    private final RemoveTagWorker worker;

    public RemoveTagHandler(final RemoveTagWorker w) {
        worker = Objects.requireNonNull(w);
    }

    @Override
    public RemoveTagResult handle(final RemoveTagCommand cmd) {
        try (final var p = worker.work()) {
            final var h = Hash.fromString(cmd.wallpaperHash());
            final var n = new Name(cmd.tagName());

            final var w = p.wallpaperRepository().findByHash(h).orElseThrow(
                    () -> new IllegalArgumentException("wallpaper not found"));

            final var t = p.tagRepository().findByName(n).orElseThrow(
                    () -> new IllegalArgumentException("tag not found"));

            if (w.getTag(t.id()) == null) {
                throw new IllegalArgumentException("tag not attached");
            }

            w.removeTag(t.id());

            p.wallpaperRepository().save(w);
            p.commit();

            return new RemoveTagResult();
        } catch (final Exception e) {
            // TODO: handle
            return null;
        }
    }
}
