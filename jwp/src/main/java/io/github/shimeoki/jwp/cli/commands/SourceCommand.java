package io.github.shimeoki.jwp.cli.commands;

import io.github.shimeoki.jwp.cli.Command;
import io.github.shimeoki.jwp.cli.commands.source.CreateCommand;
import io.github.shimeoki.jwp.config.App;

public final class SourceCommand extends Command {

    public SourceCommand(final App app) {
        super("source");
        addCommand(new CreateCommand(app));
    }

    @Override
    public void run(final String[] args) {
        System.out.println(help());
    }

    @Override
    public String description() {
        return "Manage sources for your wallpapers";
    }
}
