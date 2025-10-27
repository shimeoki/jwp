package io.github.shimeoki.jwp.cli.commands;

import io.github.shimeoki.jwp.cli.Command;
import io.github.shimeoki.jwp.cli.commands.source.AddCommand;
import io.github.shimeoki.jwp.cli.commands.source.CreateCommand;
import io.github.shimeoki.jwp.cli.commands.source.DeleteCommand;
import io.github.shimeoki.jwp.cli.commands.source.ListCommand;
import io.github.shimeoki.jwp.cli.commands.source.RemoveCommand;
import io.github.shimeoki.jwp.config.App;

public final class SourceCommand extends Command {

    public SourceCommand(final App app) {
        super("source");
        addCommand(new CreateCommand(app));
        addCommand(new DeleteCommand(app));
        addCommand(new ListCommand(app));
        addCommand(new AddCommand(app));
        addCommand(new RemoveCommand(app));
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
