package io.github.shimeoki.jwp;

import java.util.Objects;

import io.github.shimeoki.jwp.cli.Command;
import io.github.shimeoki.jwp.cli.Runner;
import io.github.shimeoki.jwp.cli.runners.SessionRunner;
import io.github.shimeoki.jwp.cli.runners.TagRunner;
import io.github.shimeoki.jwp.cli.runners.tag.CreateTagRunner;
import io.github.shimeoki.jwp.cli.runners.tag.ListTagsRunner;
import io.github.shimeoki.jwp.config.App;

public final class CLI implements Runner {

    private final App app;
    private final Command command;

    // TODO: refactor into smaller functions
    public CLI(final App app) {
        this.app = Objects.requireNonNull(app);

        // ideally this can be done dynamically to change the config
        // from the commands (for example, to get --config command working),
        // but it works for now
        app.open();

        final var handlers = this.app.handlers();

        command = new Command("jwp", (cmd, _) -> {
            System.out.print(cmd.help());
        });

        final var session = new Command("session",
                new SessionRunner(this));

        final var tag = new Command("tag",
                new TagRunner());

        final var tagCreate = new Command("create",
                new CreateTagRunner(handlers.createTag()));

        final var tagList = new Command("list",
                new ListTagsRunner(handlers.listTags()));

        tag.addCommand(tagCreate);
        tag.addCommand(tagList);

        command.addCommand(tag);
        command.addCommand(session);
    }

    @Override
    public void run(Command cmd, String[] args) {
        command.execute(args);
    }
}
