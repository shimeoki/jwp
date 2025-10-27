package io.github.shimeoki.jwp.app.actions.sourcedelete;

import java.util.Objects;

import io.github.shimeoki.jwp.app.Handler;
import io.github.shimeoki.jwp.domain.values.ID;

public final class DeleteSourceHandler
        implements Handler<DeleteSourceCommand, DeleteSourceResult> {

    private final DeleteSourceWorker worker;

    public DeleteSourceHandler(final DeleteSourceWorker w) {
        worker = Objects.requireNonNull(w);
    }

    @Override
    public DeleteSourceResult handle(final DeleteSourceCommand cmd) {
        try (final var p = worker.work()) {
            final var id = ID.fromString(cmd.id());

            final var s = p.sourceRepository().findByID(id).orElseThrow(
                    () -> new IllegalArgumentException("source not found"));

            p.sourceRepository().delete(s.id());
            p.commit();

            return new DeleteSourceResult();
        } catch (final Exception e) {
            // TODO: handle
            return null;
        }
    }
}
