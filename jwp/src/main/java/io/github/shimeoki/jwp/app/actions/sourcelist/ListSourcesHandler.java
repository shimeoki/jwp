package io.github.shimeoki.jwp.app.actions.sourcelist;

import java.util.Objects;

import io.github.shimeoki.jwp.app.ApplicationException;
import io.github.shimeoki.jwp.app.Handler;

public final class ListSourcesHandler
        implements Handler<ListSourcesQuery, ListSourcesResult> {

    private final ListSourcesWorker worker;

    public ListSourcesHandler(final ListSourcesWorker w) {
        worker = Objects.requireNonNull(w);
    }

    @Override
    public ListSourcesResult handle(final ListSourcesQuery qry) {
        try (final var p = worker.work()) {
            return new ListSourcesResult(p.sourceRepository().findAll()
                    .map((s) -> new ListSourcesResult.Source(
                            s.id().toString(),
                            s.name().toString(),
                            s.link()))
                    .toList());
        } catch (final Exception e) {
            throw new ApplicationException("sourcelist", e);
        }
    }
}
