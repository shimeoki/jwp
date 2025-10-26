package io.github.shimeoki.jwp.cli.commands.image;

import java.io.IOException;
import java.util.Objects;

import io.github.shimeoki.jwp.app.actions.wallpapershow.ShowWallpaperQuery;
import io.github.shimeoki.jwp.cli.Command;
import io.github.shimeoki.jwp.cli.Runner;
import io.github.shimeoki.jwp.cli.Runners;
import io.github.shimeoki.jwp.config.App;

public final class ShowCommand extends Command {

    private static final Runner check = Runners.exactArgs(1);

    private final App app;

    public ShowCommand(final App app) {
        super("show [hash]");
        this.app = Objects.requireNonNull(app);
    }

    @Override
    public void run(final String[] args) {
        check.run(args);

        final var handler = app.handlers().showWallpaper();

        final var r = handler.handle(new ShowWallpaperQuery(args[0]));

        try (final var img = r.image()) {
            // NOTE: right now just write raw data for kitty icat, for example
            System.out.writeBytes(img.readAllBytes());
            System.out.println();
        } catch (final IOException e) {
            throw new RuntimeException("error while showing the image", e);
        }
    }

    @Override
    public String description() {
        return "Show the wallpaper image";
    }
}
