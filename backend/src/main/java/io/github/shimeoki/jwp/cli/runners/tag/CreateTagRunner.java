package io.github.shimeoki.jwp.cli.runners.tag;

import java.util.Arrays;

import io.github.shimeoki.jwp.application.actions.tagcreate.CreateTagCommand;
import io.github.shimeoki.jwp.application.actions.tagcreate.CreateTagHandler;
import io.github.shimeoki.jwp.cli.Runner;
import io.github.shimeoki.jwp.infrastructure.inmemory.InMemoryTagRepository;

public final class CreateTagRunner implements Runner {

    @Override
    public void run(final String[] args) {
        System.out.println(Arrays.toString(args));

        if (args.length != 1) {
            throw new IllegalArgumentException("no name provided");
        }

        final var repo = new InMemoryTagRepository();
        final var handler = new CreateTagHandler(repo);

        handler.handle(new CreateTagCommand(args[0]));
    }
}
