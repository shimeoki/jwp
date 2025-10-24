package io.github.shimeoki.jwp.cli.commands.tag;

import java.util.Objects;

import io.github.shimeoki.jwp.app.actions.tagcreate.CreateTagCommand;
import io.github.shimeoki.jwp.cli.Command;
import io.github.shimeoki.jwp.cli.Runner;
import io.github.shimeoki.jwp.cli.Runners;
import io.github.shimeoki.jwp.config.App;

public final class CreateCommand extends Command {

    private static final Runner check = Runners.exactArgs(1);

    private final App app;

    public CreateCommand(final App app) {
        super("create [name]");
        this.app = Objects.requireNonNull(app);
    }

    @Override
    public void run(final String[] args) {
        check.run(args);

        final var handler = app.handlers().createTag();

        handler.handle(new CreateTagCommand(args[0]));
    }

    @Override
    public String description() {
        return "Create a tag with an unique name";
    }
}
