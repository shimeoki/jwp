package io.github.shimeoki.jwp.cli.commands.image;

import java.util.Objects;

import io.github.shimeoki.jwp.app.actions.wallpaperfind.FindWallpaperQuery;
import io.github.shimeoki.jwp.cli.Command;
import io.github.shimeoki.jwp.cli.Runner;
import io.github.shimeoki.jwp.cli.Runners;
import io.github.shimeoki.jwp.config.App;

public final class FindCommand extends Command {

    private static final Runner check = Runners.exactArgs(1);

    private final App app;

    public FindCommand(final App app) {
        super("find [hash]");
        this.app = Objects.requireNonNull(app);
    }

    @Override
    public void run(String[] args) {
        check.run(args);

        final var handler = app.handlers().findWallpaper();

        final var r = handler.handle(new FindWallpaperQuery(args[0]));
        System.out.println(r.format());
    }

    @Override
    public String description() {
        return "Find the wallpaper metadata";
    }
}
