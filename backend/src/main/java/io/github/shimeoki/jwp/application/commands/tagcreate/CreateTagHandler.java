package io.github.shimeoki.jwp.application.commands.tagcreate;

import java.util.Objects;

import io.github.shimeoki.jwp.application.CommandHandler;
import io.github.shimeoki.jwp.domain.entities.Tag;
import io.github.shimeoki.jwp.domain.repositories.TagRepository;
import io.github.shimeoki.jwp.domain.values.Name;

public final class CreateTagHandler
        implements CommandHandler<CreateTagCommand, CreateTagResult> {

    private final TagRepository tags;

    public CreateTagHandler(final TagRepository tags) {
        this.tags = Objects.requireNonNull(tags);
    }

    @Override
    public CreateTagResult handle(final CreateTagCommand cmd) {
        final var name = new Name(cmd.name());
        tags.findByName(name).orElseThrow(
                () -> new IllegalArgumentException("tag already exists"));

        tags.save(new Tag(name));
        return new CreateTagResult();
    }
}
