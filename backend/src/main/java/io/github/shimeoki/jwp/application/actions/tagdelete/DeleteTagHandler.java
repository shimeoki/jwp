package io.github.shimeoki.jwp.application.actions.tagdelete;

import java.util.Objects;

import io.github.shimeoki.jwp.application.Handler;
import io.github.shimeoki.jwp.domain.repositories.TagRepository;
import io.github.shimeoki.jwp.domain.values.Name;

public final class DeleteTagHandler
        implements Handler<DeleteTagCommand, DeleteTagResult> {

    private final TagRepository tags;

    public DeleteTagHandler(final TagRepository tags) {
        this.tags = Objects.requireNonNull(tags);
    }

    @Override
    public DeleteTagResult handle(final DeleteTagCommand cmd) {
        final var name = new Name(cmd.name());
        final var tag = tags.findByName(name).orElseThrow(
                () -> new IllegalArgumentException("tag not found"));

        tags.delete(tag.id());
        return new DeleteTagResult();
    }
}
