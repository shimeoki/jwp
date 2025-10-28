package io.github.shimeoki.jwp.app.actions.aliasremove;

import java.util.Objects;

import io.github.shimeoki.jwp.app.Handler;
import io.github.shimeoki.jwp.domain.values.Hash;
import io.github.shimeoki.jwp.domain.values.Name;

public final class RemoveAliasHandler
        implements Handler<RemoveAliasCommand, RemoveAliasResult> {

    private final RemoveAliasWorker worker;

    public RemoveAliasHandler(final RemoveAliasWorker w) {
        worker = Objects.requireNonNull(w);
    }

    @Override
    public RemoveAliasResult handle(final RemoveAliasCommand cmd) {
        try (final var p = worker.work()) {
            final var h = Hash.fromString(cmd.wallpaperHash());

            final var w = p.wallpaperRepository().findByHash(h).orElseThrow(
                    () -> new IllegalArgumentException("wallpaper not found"));

            // NOTE: probably there should be a contstraint to make unique
            // aliases by name for a wallpaper, but works for now

            final var n = new Name(cmd.aliasName());

            final var aliases = p.aliasRepository()
                    .findByWallpaperID(w.id()).toList();

            for (final var a : aliases) {
                if (a.name().equals(n)) {
                    p.aliasRepository().delete(a.id());
                    p.commit();
                    return new RemoveAliasResult();
                }
            }

            throw new IllegalArgumentException("alias not found");
        } catch (final Exception e) {
            // TODO: handle
            return null;
        }
    }
}
