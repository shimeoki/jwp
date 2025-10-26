package io.github.shimeoki.jwp.app.actions.sourcecreate;

import java.util.Objects;

import io.github.shimeoki.jwp.app.Handler;
import io.github.shimeoki.jwp.domain.entities.Source;
import io.github.shimeoki.jwp.domain.values.Name;

public final class CreateSourceHandler
        implements Handler<CreateSourceCommand, CreateSourceResult> {

    private final CreateSourceWorker worker;

    public CreateSourceHandler(final CreateSourceWorker w) {
        worker = Objects.requireNonNull(w);
    }

    @Override
    public CreateSourceResult handle(final CreateSourceCommand cmd) {
        try (final var p = worker.work()) {
            final var s = new Source(new Name(cmd.name()), cmd.link());

            p.sourceRepository().save(s);
            p.commit();

            return new CreateSourceResult(s.id().toString());
        } catch (final Exception e) {
            // TODO: handle
            return null;
        }
    }
}
