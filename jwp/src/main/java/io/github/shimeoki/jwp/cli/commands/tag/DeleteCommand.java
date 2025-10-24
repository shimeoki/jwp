package io.github.shimeoki.jwp.cli.commands.tag;

import java.util.Objects;

import io.github.shimeoki.jwp.app.actions.tagdelete.DeleteTagCommand;
import io.github.shimeoki.jwp.cli.Command;
import io.github.shimeoki.jwp.cli.Runner;
import io.github.shimeoki.jwp.cli.Runners;
import io.github.shimeoki.jwp.config.App;

public final class DeleteCommand extends Command {

    private static final Runner check = Runners.exactArgs(1);

    private final App app;

    public DeleteCommand(final App app) {
        super("delete [name]");
        this.app = Objects.requireNonNull(app);
    }

    @Override
    public void run(final String[] args) {
        check.run(args);

        final var handler = app.handlers().deleteTag();

        handler.handle(new DeleteTagCommand(args[0]));
    }

    @Override
    public String description() {
        return "Delete a tag with the specified name";
    }
}
