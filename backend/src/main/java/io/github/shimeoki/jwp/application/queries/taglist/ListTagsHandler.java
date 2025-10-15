package io.github.shimeoki.jwp.application.queries.taglist;

import java.util.Objects;

import io.github.shimeoki.jwp.application.QueryHandler;
import io.github.shimeoki.jwp.domain.entities.Tag;
import io.github.shimeoki.jwp.domain.repositories.TagRepository;

public final class ListTagsHandler
        implements QueryHandler<ListTagsQuery, ListTagsResult> {

    private final TagRepository tags;

    public ListTagsHandler(final TagRepository tags) {
        this.tags = Objects.requireNonNull(tags);
    }

    @Override
    public ListTagsResult handle(final ListTagsQuery qry) {
        return new ListTagsResult(
                this.tags.findAll().map(Tag::getName).toArray(String[]::new));
    }

}
