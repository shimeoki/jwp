package io.github.shimeoki.jwp.cli.commands.tag;

import java.util.Objects;

import io.github.shimeoki.jwp.app.actions.tagrename.RenameTagCommand;
import io.github.shimeoki.jwp.cli.Command;
import io.github.shimeoki.jwp.cli.Runner;
import io.github.shimeoki.jwp.cli.Runners;
import io.github.shimeoki.jwp.config.App;

public final class RenameCommand extends Command {

    private static final Runner check = Runners.exactArgs(2);

    private final App app;

    public RenameCommand(final App app) {
        super("rename [before] [after]");
        this.app = Objects.requireNonNull(app);
    }

    @Override
    public void run(String[] args) {
        check.run(args);

        final var handler = app.handlers().renameTag();

        handler.handle(new RenameTagCommand(args[0], args[1]));
    }

    @Override
    public String description() {
        return "Rename a tag: merge the 'before' tag with the 'after'";
    }
}
