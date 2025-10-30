package io.github.shimeoki.jwp.app.actions.aliaslist;

import java.util.Objects;

import io.github.shimeoki.jwp.app.ApplicationException;
import io.github.shimeoki.jwp.app.Handler;
import io.github.shimeoki.jwp.app.InvalidRelationException;

public final class ListAliasesHandler
        implements Handler<ListAliasesQuery, ListAliasesResult> {

    private final ListAliasesWorker worker;

    public ListAliasesHandler(final ListAliasesWorker w) {
        worker = Objects.requireNonNull(w);
    }

    @Override
    public ListAliasesResult handle(final ListAliasesQuery qry) {
        try (final var p = worker.work()) {
            final var aliases = p.aliasRepository().findAll();

            final var lines = aliases.stream().map((a) -> {
                final var wid = a.wallpaperID();

                final var w = p.wallpaperRepository().findByID(wid).orElseThrow(
                        () -> new InvalidRelationException("alias", false));

                return String.format("%s -> %s",
                        a.name().toString(),
                        w.hash().toString());
            }).toArray(String[]::new);

            return new ListAliasesResult(lines);
        } catch (final Exception e) {
            throw new ApplicationException("aliaslist", e);
        }
    }
}
