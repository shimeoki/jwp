package io.github.shimeoki.jwp.application.actions.taglist;

import java.util.Objects;

import io.github.shimeoki.jwp.application.Handler;
import io.github.shimeoki.jwp.domain.entities.Tag;
import io.github.shimeoki.jwp.domain.repositories.TagRepository;

public final class ListTagsHandler
        implements Handler<ListTagsQuery, ListTagsResult> {

    private final TagRepository tags;

    public ListTagsHandler(final TagRepository tags) {
        this.tags = Objects.requireNonNull(tags);
    }

    @Override
    public ListTagsResult handle(final ListTagsQuery qry) {
        return new ListTagsResult(
                tags.findAll().map(Tag::name).toArray(String[]::new));
    }
}
