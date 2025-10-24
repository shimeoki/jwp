package io.github.shimeoki.jwp.cli;

import io.github.shimeoki.jwp.cli.commands.SessionCommand;
import io.github.shimeoki.jwp.cli.commands.TagCommand;
import io.github.shimeoki.jwp.config.App;

public final class Root extends Command {

    public Root(final App app) {
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
