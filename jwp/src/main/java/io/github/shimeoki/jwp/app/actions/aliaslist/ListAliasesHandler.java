package io.github.shimeoki.jwp.app.actions.aliaslist;

import java.util.Objects;

import io.github.shimeoki.jwp.app.Handler;

public final class ListAliasesHandler
        implements Handler<ListAliasesQuery, ListAliasesResult> {

    private final ListAliasesWorker worker;

    public ListAliasesHandler(final ListAliasesWorker w) {
        worker = Objects.requireNonNull(w);
    }

    @Override
    public ListAliasesResult handle(final ListAliasesQuery qry) {
        try (final var p = worker.work()) {
            return new ListAliasesResult(p.aliasRepository().findAll().map(
                    (a) -> String.format("%s -> %s",
                            a.name().toString(), a.wallpaperID().toString()))
                    .toArray(String[]::new));
        } catch (final Exception e) {
            // TODO: handle
            return null;
        }
    }
}
