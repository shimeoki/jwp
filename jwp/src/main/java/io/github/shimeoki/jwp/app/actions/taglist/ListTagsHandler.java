package io.github.shimeoki.jwp.app.actions.taglist;

import java.util.Objects;

import io.github.shimeoki.jwp.app.Handler;
import io.github.shimeoki.jwp.domain.entities.Tag;

public final class ListTagsHandler
        implements Handler<ListTagsQuery, ListTagsResult> {

    private final ListTagsWorker worker;

    public ListTagsHandler(final ListTagsWorker w) {
        worker = Objects.requireNonNull(w);
    }

    @Override
    public ListTagsResult handle(final ListTagsQuery qry) {
        try (final var p = worker.work()) {
            final var tags = p.tagRepository();

            final var all = tags.findAll();
            final var names = all.map(Tag::name).toArray(String[]::new);
            p.commit();

            return new ListTagsResult(names);
        } catch (final Exception e) {
            // TODO: handle
            return null;
        }
    }
}
