package io.github.shimeoki.jwp.cli.runners.tag;

import io.github.shimeoki.jwp.app.actions.tagcreate.CreateTagCommand;
import io.github.shimeoki.jwp.app.actions.tagcreate.CreateTagHandler;
import io.github.shimeoki.jwp.cli.Runner;
import io.github.shimeoki.jwp.infra.inmemory.InMemoryTagRepository;

public final class CreateTagRunner implements Runner {

    @Override
    public void run(final String[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException("no name provided");
        }

        final var repo = new InMemoryTagRepository();
        final var handler = new CreateTagHandler(repo);

        handler.handle(new CreateTagCommand(args[0]));
    }
}
