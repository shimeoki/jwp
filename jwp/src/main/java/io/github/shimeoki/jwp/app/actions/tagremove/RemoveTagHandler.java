package io.github.shimeoki.jwp.app.actions.tagremove;

import java.util.Objects;

import io.github.shimeoki.jwp.app.ApplicationException;
import io.github.shimeoki.jwp.app.Handler;
import io.github.shimeoki.jwp.app.InvalidRelationException;
import io.github.shimeoki.jwp.app.NotFoundException;
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
                    () -> new NotFoundException(
                            "wallpaper", "hash", h.toString()));

            final var t = p.tagRepository().findByName(n).orElseThrow(
                    () -> new NotFoundException(
                            "tag", "name", n.toString()));

            if (w.getTag(t.id()) == null) {
                throw new InvalidRelationException("tag", false);
            }

            w.removeTag(t.id());

            p.wallpaperRepository().save(w);
            p.commit();

            return new RemoveTagResult();
        } catch (final Exception e) {
            throw new ApplicationException("tagremove", e);
        }
    }
}
