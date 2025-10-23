package io.github.shimeoki.jwp.cli.runners.tag;

import java.util.Objects;

import io.github.shimeoki.jwp.app.actions.tagcreate.CreateTagCommand;
import io.github.shimeoki.jwp.app.actions.tagcreate.CreateTagHandler;
import io.github.shimeoki.jwp.cli.Command;
import io.github.shimeoki.jwp.cli.Runner;

public final class CreateTagRunner implements Runner {

    private final CreateTagHandler handler;

    public CreateTagRunner(final CreateTagHandler h) {
        handler = Objects.requireNonNull(h);
    }

    @Override
    public void run(final Command cmd, final String[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException("no name provided");
        }

        handler.handle(new CreateTagCommand(args[0]));
    }
}
