package io.github.shimeoki.jwp.cli;

import io.github.shimeoki.jwp.cli.runners.SessionRunner;

public final class App implements Runner {

    private final Command root;

    public App() {
        root = new Command("jwp", (cmd, _) -> {
            System.out.print(cmd.help());
        });

        final var session = new Command("session", new SessionRunner());

        root.addCommand(session);
    }

    @Override
    public void run(final Command cmd, final String[] args) {
        root.execute(args);
    }
}
