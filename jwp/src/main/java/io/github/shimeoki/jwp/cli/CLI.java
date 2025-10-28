package io.github.shimeoki.jwp.cli;

import java.util.Objects;

import io.github.shimeoki.jwp.cli.commands.RootCommand;
import io.github.shimeoki.jwp.config.App;

public final class CLI implements Runner {

    private final Command root;

    public CLI(final App app) {
        Objects.requireNonNull(app);
        this.root = new RootCommand(app);

        // ideally this can be done dynamically to change the config
        // from the commands (for example, to get --config command working),
        // but it works for now
        app.open();
    }

    @Override
    public void run(final String[] args) {
        try {
            root.execute(args);
        } catch (final Exception e) {
            System.err.printf("error: %s\n", e.getMessage());
        }
    }
}
