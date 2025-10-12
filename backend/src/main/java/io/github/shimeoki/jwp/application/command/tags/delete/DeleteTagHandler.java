package io.github.shimeoki.jwp.application.command.tags.delete;

import java.util.Objects;

import io.github.shimeoki.jwp.application.CommandHandler;
import io.github.shimeoki.jwp.domain.repositories.TagRepository;
import io.github.shimeoki.jwp.domain.values.Name;

public class DeleteTagHandler
        implements CommandHandler<DeleteTagCommand, DeleteTagResult> {

    private final TagRepository tags;

    public DeleteTagHandler(TagRepository tags) {
        this.tags = Objects.requireNonNull(tags);
    }

    @Override
    public DeleteTagResult handle(DeleteTagCommand command) {
        final var name = new Name(command.name());

        final var tag = this.tags.findByName(name).orElseThrow(
                () -> new IllegalArgumentException(" tag not found"));

        this.tags.delete(tag.getID());

        return new DeleteTagResult();
    }
}
