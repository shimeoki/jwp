package io.github.shimeoki.jwp.cli.runners.tag;

import java.util.Objects;

import io.github.shimeoki.jwp.app.actions.tagrename.RenameTagCommand;
import io.github.shimeoki.jwp.app.actions.tagrename.RenameTagHandler;
import io.github.shimeoki.jwp.cli.Command;
import io.github.shimeoki.jwp.cli.Runner;

public final class RenameTagRunner implements Runner {

    private final RenameTagHandler handler;

    public RenameTagRunner(final RenameTagHandler h) {
        handler = Objects.requireNonNull(h);
    }

    @Override
    public void run(Command cmd, String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("not enough arguments");
        }

        handler.handle(new RenameTagCommand(args[0], args[1]));
    }
}
