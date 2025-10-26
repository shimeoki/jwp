package io.github.shimeoki.jwp.cli.commands;

import io.github.shimeoki.jwp.cli.Command;
import io.github.shimeoki.jwp.cli.commands.image.CreateCommand;
import io.github.shimeoki.jwp.config.App;

public final class ImageCommand extends Command {

    public ImageCommand(final App app) {
        super("image");
        addCommand(new CreateCommand(app));
    }

    @Override
    public void run(final String[] args) {
        System.out.println(help());
    }

    @Override
    public String description() {
        return "Wallpaper and image related commands";
    }
}
