package io.github.shimeoki.jwp.cli.commands;

import io.github.shimeoki.jwp.cli.Command;
import io.github.shimeoki.jwp.cli.commands.alias.AddCommand;
import io.github.shimeoki.jwp.config.App;

public final class AliasCommand extends Command {

    public AliasCommand(final App app) {
        super("alias");
        addCommand(new AddCommand(app));
    }

    @Override
    public void run(final String[] args) {
        System.out.println(help());
    }

    @Override
    public String description() {
        return "Manage aliases for your wallpapers";
    }
}
