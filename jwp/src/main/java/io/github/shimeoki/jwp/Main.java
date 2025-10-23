package io.github.shimeoki.jwp;

import io.github.shimeoki.jwp.config.App;
import io.github.shimeoki.jwp.config.Config;

public final class Main {

    public static void main(final String[] args) {
        final var cfg = Config.defaults();
        final var app = new App(cfg);

        final var cli = new CLI(app);
        cli.run(null, args);
    }
}
