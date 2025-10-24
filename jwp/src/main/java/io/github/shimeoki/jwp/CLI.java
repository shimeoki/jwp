package io.github.shimeoki.jwp;

import java.util.Objects;

import io.github.shimeoki.jwp.cli.Command;
import io.github.shimeoki.jwp.cli.Root;
import io.github.shimeoki.jwp.cli.Runner;
import io.github.shimeoki.jwp.config.App;

public final class CLI implements Runner {

    private final Command root;

    // TODO: refactor into smaller functions
    public CLI(final App app) {
        Objects.requireNonNull(app);
        this.root = new Root(app);

        // ideally this can be done dynamically to change the config
        // from the commands (for example, to get --config command working),
        // but it works for now
        app.open();
    }

    @Override
    public void run(String[] args) {
        root.execute(args);
    }
}
