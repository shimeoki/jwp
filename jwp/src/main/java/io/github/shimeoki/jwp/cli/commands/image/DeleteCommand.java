package io.github.shimeoki.jwp.cli.commands.image;

import java.util.Objects;

import io.github.shimeoki.jwp.app.actions.wallpaperdelete.DeleteWallpaperCommand;
import io.github.shimeoki.jwp.cli.Command;
import io.github.shimeoki.jwp.cli.Runner;
import io.github.shimeoki.jwp.cli.Runners;
import io.github.shimeoki.jwp.config.App;

public final class DeleteCommand extends Command {

    private static final Runner check = Runners.exactArgs(1);

    private final App app;

    public DeleteCommand(final App app) {
        super("delete [hash]");
        this.app = Objects.requireNonNull(app);
    }

    @Override
    public void run(String[] args) {
        check.run(args);

        final var handler = app.handlers().deleteWallpaper();

        handler.handle(new DeleteWallpaperCommand(args[0]));
    }

    @Override
    public String description() {
        return "Delete a wallpaper with the image from the store";
    }
}
