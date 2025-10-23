package io.github.shimeoki.jwp.app.actions.tagrename;

import java.util.Objects;

import io.github.shimeoki.jwp.app.Handler;
import io.github.shimeoki.jwp.domain.values.Name;

public final class RenameTagHandler
        implements Handler<RenameTagCommand, RenameTagResult> {

    private final RenameTagWorker worker;

    public RenameTagHandler(final RenameTagWorker w) {
        worker = Objects.requireNonNull(w);
    }

    @Override
    public RenameTagResult handle(final RenameTagCommand cmd) {
        try (final var p = worker.work()) {
            final var tags = p.tagRepository();
            final var wallpapers = p.wallpaperRepository();

            final var before = new Name(cmd.before());
            final var tag = tags.findByName(before).orElseThrow(
                    () -> new IllegalArgumentException("tag not found"));

            final var after = new Name(cmd.after());
            tag.rename(after);

            tags.findByName(after).ifPresent((existent) -> {
                final var id = existent.id();
                final var walls = wallpapers.findByTagID(id);

                walls.forEach((wall) -> {
                    wall.removeTag(id);
                    wall.addTag(tag);
                    wallpapers.save(wall);
                });

                tags.delete(id);
            });

            tags.save(tag);
            p.commit();

            return new RenameTagResult();
        } catch (final Exception e) {
            // TODO: handle
            return null;
        }
    }
}
