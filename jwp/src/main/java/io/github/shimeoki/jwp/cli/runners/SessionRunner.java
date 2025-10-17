package io.github.shimeoki.jwp.cli.runners;

import java.util.Scanner;

import io.github.shimeoki.jwp.cli.App;
import io.github.shimeoki.jwp.cli.Command;
import io.github.shimeoki.jwp.cli.Runner;

public final class SessionRunner implements Runner {

    private static boolean running = false;

    @Override
    public void run(final Command cmd, final String[] args) {
        if (running) {
            throw new IllegalStateException("recursive sessions are not allowed");
        }

        running = true;

        try (var s = new Scanner(System.in)) {
            System.out.print("> ");

            while (s.hasNextLine()) {
                final var line = s.nextLine();
                final var opts = line.trim().split("\\s+"); // just by spaces

                try {
                    App.main(opts);
                } catch (final Exception e) {
                    System.out.printf("error: %s\n", e.getMessage());
                }

                System.out.print("> ");
            }
        }

        running = false;
    }
}
