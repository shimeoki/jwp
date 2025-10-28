package io.github.shimeoki.jwp.app.actions.tagadd;

import java.util.Objects;

import io.github.shimeoki.jwp.app.ApplicationException;
import io.github.shimeoki.jwp.app.Handler;
import io.github.shimeoki.jwp.app.InvalidRelationException;
import io.github.shimeoki.jwp.app.NotFoundException;
import io.github.shimeoki.jwp.domain.values.Hash;
import io.github.shimeoki.jwp.domain.values.Name;

public final class AddTagHandler
        implements Handler<AddTagCommand, AddTagResult> {

    private final AddTagWorker worker;

    public AddTagHandler(final AddTagWorker w) {
        worker = Objects.requireNonNull(w);
    }

    @Override
    public AddTagResult handle(final AddTagCommand cmd) {
        try (final var p = worker.work()) {
            final var h = Hash.fromString(cmd.wallpaperHash());
            final var n = new Name(cmd.tagName());

            final var w = p.wallpaperRepository().findByHash(h).orElseThrow(
                    () -> new NotFoundException(
                            "wallpaper", "hash", h.toString()));

            final var t = p.tagRepository().findByName(n).orElseThrow(
                    () -> new NotFoundException(
                            "tag", "name", n.toString()));

            if (w.getTag(t.id()) != null) {
                throw new InvalidRelationException("tag", true);
            }

            w.addTag(t);

            p.wallpaperRepository().save(w);
            p.commit();

            return new AddTagResult();
        } catch (final Exception e) {
            throw new ApplicationException("tagadd", e);
        }
    }
}
