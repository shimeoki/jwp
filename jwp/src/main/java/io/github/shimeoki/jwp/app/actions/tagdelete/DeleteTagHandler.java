package io.github.shimeoki.jwp.app.actions.tagdelete;

import java.util.Objects;

import io.github.shimeoki.jwp.app.ApplicationException;
import io.github.shimeoki.jwp.app.Handler;
import io.github.shimeoki.jwp.app.NotFoundException;
import io.github.shimeoki.jwp.domain.values.Name;

public final class DeleteTagHandler
        implements Handler<DeleteTagCommand, DeleteTagResult> {

    private final DeleteTagWorker worker;

    public DeleteTagHandler(final DeleteTagWorker w) {
        worker = Objects.requireNonNull(w);
    }

    @Override
    public DeleteTagResult handle(final DeleteTagCommand cmd) {
        try (final var p = worker.work()) {
            final var tags = p.tagRepository();

            final var name = new Name(cmd.name());
            final var tag = tags.findByName(name).orElseThrow(
                    () -> new NotFoundException(
                            "tag", "name", name.toString()));

            tags.delete(tag.id());
            p.commit();

            return new DeleteTagResult();
        } catch (final Exception e) {
            throw new ApplicationException("tagdelete", e);
        }
    }
}
