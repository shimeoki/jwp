package io.github.shimeoki.jwp.cli.runners.tag;

import java.util.Objects;

import io.github.shimeoki.jwp.app.actions.tagdelete.DeleteTagCommand;
import io.github.shimeoki.jwp.app.actions.tagdelete.DeleteTagHandler;
import io.github.shimeoki.jwp.cli.Command;
import io.github.shimeoki.jwp.cli.Runner;

public final class DeleteTagRunner implements Runner {

    private final DeleteTagHandler handler;

    public DeleteTagRunner(final DeleteTagHandler h) {
        handler = Objects.requireNonNull(h);
    }

    @Override
    public void run(Command cmd, String[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException("no name provided");
        }

        handler.handle(new DeleteTagCommand(args[0]));
    }
}
