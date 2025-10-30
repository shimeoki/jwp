package io.github.shimeoki.jwp.cli.commands.tag;

import java.util.Objects;

import io.github.shimeoki.jwp.app.actions.taglist.ListTagsQuery;
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

        final var handler = app.handlers().listTags();
        final var result = handler.handle(new ListTagsQuery());
        final var lines = String.join("\n", result.names());

        System.out.println(lines);
    }

    @Override
    public String description() {
        return "Print all available tags";
    }
}
