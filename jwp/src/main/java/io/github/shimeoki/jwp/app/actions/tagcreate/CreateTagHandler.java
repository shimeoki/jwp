package io.github.shimeoki.jwp.app.actions.tagcreate;

import java.util.Objects;

import io.github.shimeoki.jwp.app.AlreadyExistsException;
import io.github.shimeoki.jwp.app.ApplicationException;
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
            tags.findByName(name).ifPresent((_) -> {
                throw new AlreadyExistsException(
                        "tag", "name", name.toString());
            });

            tags.save(new Tag(name));
            p.commit();

            return new CreateTagResult();
        } catch (final Exception e) {
            throw new ApplicationException("tagcreate", e);
        }
    }
}
