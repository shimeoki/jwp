package io.github.shimeoki.jwp.cli.commands.source;

import java.util.Objects;

import io.github.shimeoki.jwp.app.actions.sourcedelete.DeleteSourceCommand;
import io.github.shimeoki.jwp.cli.Command;
import io.github.shimeoki.jwp.cli.Runner;
import io.github.shimeoki.jwp.cli.Runners;
import io.github.shimeoki.jwp.config.App;

public final class DeleteCommand extends Command {

    private static final Runner check = Runners.exactArgs(1);

    private final App app;

    public DeleteCommand(final App app) {
        super("delete [id]");
        this.app = Objects.requireNonNull(app);
    }

    @Override
    public void run(final String[] args) {
        check.run(args);

        final var handler = app.handlers().deleteSource();

        handler.handle(new DeleteSourceCommand(args[0]));
    }

    @Override
    public String description() {
        return "Delete the specified source by id";
    }
}
