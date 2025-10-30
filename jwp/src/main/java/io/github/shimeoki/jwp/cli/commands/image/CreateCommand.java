package io.github.shimeoki.jwp.cli.commands.image;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

import io.github.shimeoki.jwp.app.actions.wallpapercreate.CreateWallpaperCommand;
import io.github.shimeoki.jwp.cli.Command;
import io.github.shimeoki.jwp.cli.Runner;
import io.github.shimeoki.jwp.cli.Runners;
import io.github.shimeoki.jwp.config.App;

public final class CreateCommand extends Command {

    private static final Runner check = Runners.exactArgs(1);

    private final App app;

    public CreateCommand(final App app) {
        super("create [path]");
        this.app = Objects.requireNonNull(app);
    }

    @Override
    public void run(final String[] args) {
        check.run(args);

        final var handler = app.handlers().createWallpaper();

        final var path = Paths.get(args[0]);
        if (!Files.exists(path)) {
            throw new IllegalArgumentException("invalid path");
        }

        if (!Files.isRegularFile(path)) {
            throw new IllegalArgumentException("path is not pointing to file");
        }

        final var name = path.getFileName().toString();

        final var index = name.lastIndexOf(".");
        if (index == -1) {
            throw new IllegalArgumentException("extension is empty");
        }

        final var ext = name.substring(index + 1);

        try (final var img = Files.newInputStream(path)) {
            final var r = handler.handle(new CreateWallpaperCommand(img, ext));
            System.out.println(r.hash());
        } catch (final IOException e) {
            throw new RuntimeException("io error happened", e);
        }
    }

    @Override
    public String description() {
        return "Import a wallpaper from an image on a path";
    }
}
