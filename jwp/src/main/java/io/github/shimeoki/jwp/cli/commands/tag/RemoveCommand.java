package io.github.shimeoki.jwp.cli.commands.tag;

import java.util.Objects;

import io.github.shimeoki.jwp.app.actions.tagremove.RemoveTagCommand;
import io.github.shimeoki.jwp.cli.Command;
import io.github.shimeoki.jwp.cli.Runner;
import io.github.shimeoki.jwp.cli.Runners;
import io.github.shimeoki.jwp.config.App;

public final class RemoveCommand extends Command {

    private static final Runner check = Runners.exactArgs(2);

    private final App app;

    public RemoveCommand(final App app) {
        super("remove [hash] [name]");
        this.app = Objects.requireNonNull(app);
    }

    @Override
    public void run(final String[] args) {
        check.run(args);

        final var handler = app.handlers().removeTag();

        handler.handle(new RemoveTagCommand(args[0], args[1]));
    }

    @Override
    public String description() {
        return "Deattach a tag from wallpaper by hash";
    }
}
