package io.github.shimeoki.jwp.cli.commands;

import io.github.shimeoki.jwp.cli.Command;
import io.github.shimeoki.jwp.cli.commands.tag.CreateCommand;
import io.github.shimeoki.jwp.cli.commands.tag.DeleteCommand;
import io.github.shimeoki.jwp.cli.commands.tag.ListCommand;
import io.github.shimeoki.jwp.cli.commands.tag.RenameCommand;
import io.github.shimeoki.jwp.config.App;

public final class TagCommand extends Command {

    public TagCommand(final App app) {
        super("tag");
        addCommand(new CreateCommand(app));
        addCommand(new ListCommand(app));
        addCommand(new DeleteCommand(app));
        addCommand(new RenameCommand(app));
    }

    @Override
    public void run(final String[] args) {
        System.out.println(help());
    }

    @Override
    public String description() {
        return "Tag related commands";
    }
}
