package io.github.shimeoki.jwp.app.actions.taglist;

import java.util.Objects;

import io.github.shimeoki.jwp.app.ApplicationException;
import io.github.shimeoki.jwp.app.Handler;
import io.github.shimeoki.jwp.domain.entities.Tag;
import io.github.shimeoki.jwp.domain.values.Name;

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

            final var names = tags.findAll().stream()
                    .map(Tag::name)
                    .map(Name::toString)
                    .toArray(String[]::new);

            return new ListTagsResult(names);
        } catch (final Exception e) {
            throw new ApplicationException("taglist", e);
        }
    }
}
