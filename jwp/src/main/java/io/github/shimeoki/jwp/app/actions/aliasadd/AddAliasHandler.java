package io.github.shimeoki.jwp.app.actions.aliasadd;

import java.util.Objects;

import io.github.shimeoki.jwp.app.Handler;
import io.github.shimeoki.jwp.domain.entities.Alias;
import io.github.shimeoki.jwp.domain.values.Hash;
import io.github.shimeoki.jwp.domain.values.Name;

public final class AddAliasHandler
        implements Handler<AddAliasCommand, AddAliasResult> {

    private final AddAliasWorker worker;

    public AddAliasHandler(final AddAliasWorker w) {
        worker = Objects.requireNonNull(w);
    }

    @Override
    public AddAliasResult handle(final AddAliasCommand cmd) {
        try (final var p = worker.work()) {
            final var h = Hash.fromString(cmd.wallpaperHash());

            final var w = p.wallpaperRepository().findByHash(h).orElseThrow(
                    () -> new IllegalArgumentException("wallpaper not found"));

            final var a = new Alias(w.id(), new Name(cmd.aliasName()));

            p.aliasRepository().save(a);
            p.commit();

            return new AddAliasResult();
        } catch (final Exception e) {
            // TODO: handle
            return null;
        }
    }
}
