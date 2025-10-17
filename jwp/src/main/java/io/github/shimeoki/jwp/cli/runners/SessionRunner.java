package io.github.shimeoki.jwp.cli.runners;

import java.util.Scanner;

import io.github.shimeoki.jwp.cli.Command;
import io.github.shimeoki.jwp.cli.Runner;
import io.github.shimeoki.jwp.cli.runners.tag.CreateTagRunner;

public final class SessionRunner implements Runner {

    private final Command root;

    public SessionRunner() {
        root = new Command("jwp", (_, _) -> {
            // TODO: help
        });

        final var tag = new Command("tag", (_, _) -> {
            // TODO: help
        });

        final var tagCreate = new Command("create", new CreateTagRunner());

        tag.addCommand(tagCreate);
        root.addCommand(tag);
    }

    @Override
    public void run(final Command cmd, final String[] args) {
        try (var s = new Scanner(System.in)) {
            System.out.print("> ");

            while (s.hasNextLine()) {
                final var line = s.nextLine();
                final var opts = line.trim().split("\\s+"); // just by spaces

                try {
                    root.execute(opts);
                } catch (final Exception e) {
                    System.out.printf("error: %s\n", e.getMessage());
                }

                System.out.print("> ");
            }
        }
    }
}
