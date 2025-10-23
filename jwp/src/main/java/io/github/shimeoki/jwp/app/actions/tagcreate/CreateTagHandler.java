package io.github.shimeoki.jwp.app.actions.tagcreate;

import java.util.Objects;

import io.github.shimeoki.jwp.app.Handler;
import io.github.shimeoki.jwp.domain.entities.Tag;
import io.github.shimeoki.jwp.domain.values.Name;

public final class CreateTagHandler
        implements Handler<CreateTagCommand, CreateTagResult> {

    private final CreateTagWorker worker;

    public CreateTagHandler(final CreateTagWorker w) {
        worker = Objects.requireNonNull(w);
    }

    @Override
    public CreateTagResult handle(final CreateTagCommand cmd) {
        try (final var p = worker.work()) {
            final var tags = p.tagRepository();

            final var name = new Name(cmd.name());
            tags.findByName(name).ifPresent(
                    (_) -> new IllegalArgumentException("tag already exists"));

            tags.save(new Tag(name));
            return new CreateTagResult();
        } catch (final Exception e) {
            // TODO: handle exception
            return null;
        }
    }
}
