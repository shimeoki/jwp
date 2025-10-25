package io.github.shimeoki.jwp.cli.commands;

import io.github.shimeoki.jwp.cli.Command;
import io.github.shimeoki.jwp.config.App;

public final class RootCommand extends Command {

    public RootCommand(final App app) {
        super("jwp");
        addCommand(new TagCommand(app));
        addCommand(new SessionCommand(this));
    }

    @Override
    public void run(final String[] args) {
        System.out.println(help());
    }

    @Override
    public String description() {
        return "Manage your wallpapers in a hashed store";
    }
}
