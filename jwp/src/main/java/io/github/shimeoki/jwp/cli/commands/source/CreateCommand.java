package io.github.shimeoki.jwp.cli.commands.source;

import java.util.Objects;

import io.github.shimeoki.jwp.app.actions.sourcecreate.CreateSourceCommand;
import io.github.shimeoki.jwp.app.actions.sourcecreate.CreateSourceResult;
import io.github.shimeoki.jwp.cli.Command;
import io.github.shimeoki.jwp.cli.Runner;
import io.github.shimeoki.jwp.cli.Runners;
import io.github.shimeoki.jwp.config.App;

public final class CreateCommand extends Command {

    private static Runner check = Runners.rangeArgs(1, 2);

    private final App app;

    public CreateCommand(final App app) {
        super("create [name] [link?]");
        this.app = Objects.requireNonNull(app);
    }

    @Override
    public void run(String[] args) {
        check.run(args);

        final var handler = app.handlers().createSource();

        CreateSourceResult r;
        if (args.length == 2) {
            r = handler.handle(new CreateSourceCommand(args[0], args[1]));
        } else {
            r = handler.handle(new CreateSourceCommand(args[0], null));
        }

        System.out.println(r.id());
    }

    @Override
    public String description() {
        return "Create a source with an optional link";
    }
}
