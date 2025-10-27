package io.github.shimeoki.jwp.cli.commands.tag;

import java.util.Objects;

import io.github.shimeoki.jwp.app.actions.tagadd.AddTagCommand;
import io.github.shimeoki.jwp.cli.Command;
import io.github.shimeoki.jwp.cli.Runner;
import io.github.shimeoki.jwp.cli.Runners;
import io.github.shimeoki.jwp.config.App;

public final class AddCommand extends Command {

    private static final Runner check = Runners.exactArgs(2);

    private final App app;

    public AddCommand(final App app) {
        super("add [hash] [name]");
        this.app = Objects.requireNonNull(app);
    }

    @Override
    public void run(final String[] args) {
        check.run(args);

        final var handler = app.handlers().addTag();

        handler.handle(new AddTagCommand(args[0], args[1]));
    }

    @Override
    public String description() {
        return "Attach a tag to a wallpaper by hash";
    }
}
