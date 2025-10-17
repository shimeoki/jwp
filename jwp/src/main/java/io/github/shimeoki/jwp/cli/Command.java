package io.github.shimeoki.jwp.cli;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class Command {

    private final String name;
    private final Runner runner;
    private final Map<String, Command> commands;

    public Command(final String name, final Runner runner) {
        this.name = Objects.requireNonNull(name);
        this.runner = Objects.requireNonNull(runner);
        this.commands = new HashMap<>();
    }

    public void addCommand(final Command cmd) {
        Objects.requireNonNull(cmd);
        commands.put(cmd.name, cmd);
    }

    public String name() {
        return name;
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
}
