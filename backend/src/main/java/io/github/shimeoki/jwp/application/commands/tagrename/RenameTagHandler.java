package io.github.shimeoki.jwp.application.commands.tagrename;

import java.util.Objects;

import io.github.shimeoki.jwp.application.CommandHandler;
import io.github.shimeoki.jwp.domain.repositories.TagRepository;
import io.github.shimeoki.jwp.domain.repositories.WallpaperRepository;
import io.github.shimeoki.jwp.domain.values.Name;

public final class RenameTagHandler
        implements CommandHandler<RenameTagCommand, RenameTagResult> {

    private final WallpaperRepository wallpapers;
    private final TagRepository tags;

    public RenameTagHandler(
            final WallpaperRepository wallpapers,
            final TagRepository tags) {

        this.wallpapers = Objects.requireNonNull(wallpapers);
        this.tags = Objects.requireNonNull(tags);
    }

    @Override
    public RenameTagResult handle(final RenameTagCommand cmd) {
        final var before = new Name(cmd.before());
        final var tag = tags.findByName(before).orElseThrow(
                () -> new IllegalArgumentException("tag not found"));

        final var after = new Name(cmd.after());
        tag.rename(after);

        tags.findByName(after).ifPresent((existent) -> {
            final var id = existent.getID();
            final var walls = wallpapers.findByTagID(id);

            walls.forEach((wall) -> {
                wall.removeTag(id);
                wall.addTag(tag);
                wallpapers.save(wall);
            });

            tags.delete(id);
        });

        tags.save(tag);
        return new RenameTagResult();
    }
}
