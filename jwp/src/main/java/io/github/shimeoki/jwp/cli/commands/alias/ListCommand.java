package io.github.shimeoki.jwp.cli.commands.alias;

import java.util.Objects;

import io.github.shimeoki.jwp.app.actions.aliaslist.ListAliasesQuery;
import io.github.shimeoki.jwp.cli.Command;
import io.github.shimeoki.jwp.cli.Runner;
import io.github.shimeoki.jwp.cli.Runners;
import io.github.shimeoki.jwp.config.App;

public final class ListCommand extends Command {

    private static final Runner check = Runners.exactArgs(0);

    private final App app;

    public ListCommand(final App app) {
        super("list");
        this.app = Objects.requireNonNull(app);
    }

    @Override
    public void run(final String[] args) {
        check.run(args);

        final var handler = app.handlers().listAliases();
        final var r = handler.handle(new ListAliasesQuery());
        final var lines = String.join("\n", r.aliases());

        System.out.println(lines);
    }

    @Override
    public String description() {
        return "Print all aliases";
    }
}
