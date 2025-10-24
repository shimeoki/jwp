package io.github.shimeoki.jwp.cli.commands;

import java.util.Objects;
import java.util.Scanner;

import io.github.shimeoki.jwp.cli.Command;
import io.github.shimeoki.jwp.cli.Runner;
import io.github.shimeoki.jwp.cli.Runners;

public final class SessionCommand extends Command {

    private static final Runner check = Runners.exactArgs(0);

    // TODO: static?
    private boolean running = false;

    private final Command root;

    public SessionCommand(final Command root) {
        super("session");
        this.root = Objects.requireNonNull(root);
    }

    @Override
    public void run(final String[] args) {
        check.run(args);

        if (running) {
            throw new IllegalStateException(
                    "recursive sessions are not allowed");
        }

        running = true;

        try (final var s = new Scanner(System.in)) {
            System.out.print("> ");

            while (s.hasNextLine()) {
                final var line = s.nextLine();
                final var opts = line.trim().split("\\s+");

                try {
                    root.execute(opts);
                } catch (final Exception e) {
                    System.out.printf("error: %s\n", e.getMessage());
                }

                System.out.print("> ");
            }
        }

        running = false;
    }

    @Override
    public String description() {
        return "Enter a session to use other commands continuosly";
    }
}
