package io.github.shimeoki.jwp;

import io.github.shimeoki.jwp.cli.App;

public final class Main {

    public static void main(final String[] args) {
        final var app = new App();
        app.run(null, args); // probably better to use non-null
    }
}
