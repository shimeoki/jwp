package io.github.shimeoki.jwp.cli.commands.alias;

import java.util.Objects;

import io.github.shimeoki.jwp.app.actions.aliasadd.AddAliasCommand;
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

        final var handler = app.handlers().addAlias();

        handler.handle(new AddAliasCommand(args[0], args[1]));
    }

    @Override
    public String description() {
        return "Give an alias for a wallpaper by hash";
    }
}
