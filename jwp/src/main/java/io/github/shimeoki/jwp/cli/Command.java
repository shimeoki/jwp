package io.github.shimeoki.jwp.cli;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// heavy inspiration from https://cobra.dev/

public final class Command {

    private final String name;
    private final String usage;

    private final Runner runner;
    private final Map<String, Command> commands;

    public Command(final String usage, final Runner runner) {
        final var parts = Objects.requireNonNull(usage).split("\\s+", 2);

        final var name = parts[0].trim().toLowerCase();
        if (name.isBlank() || !name.matches("\\w+")) {
            throw new IllegalArgumentException("invalid name");
        }

        if (parts.length > 1) {
            this.usage = String.join(" ", name, parts[1]);
        } else {
            this.usage = name;
        }

        this.name = name;
        this.runner = Objects.requireNonNull(runner);
        this.commands = new HashMap<>();
    }

    public void addCommand(final Command cmd) {
        Objects.requireNonNull(cmd);
        commands.put(cmd.name(), cmd);
    }

    public String name() {
        return name;
    }

    public String usage() {
        return usage;
    }

    public Collection<Command> commands() {
        return commands.values();
    }

    public void execute(final String[] args) {
        if (args.length > 0) {
            final var command = commands.get(args[0]);
            if (command != null) {
                command.execute(Arrays.copyOfRange(args, 1, args.length));
                return;
            }
        }

        runner.run(this, args);
    }

    public String help() {
        final var b = new StringBuilder();

        b.append("Usage: ");
        b.append(usage());
        b.append("\n");

        final var cmds = commands();
        if (cmds.size() > 0) {
            b.append("Commands:\n");

            for (final var cmd : cmds) {
                b.append("  ");
                b.append(cmd.usage());
                b.append("\n");
            }
        }

        return b.toString();
    }
}
