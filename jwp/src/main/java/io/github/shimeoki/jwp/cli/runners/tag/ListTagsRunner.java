package io.github.shimeoki.jwp.cli.runners.tag;

import java.util.Objects;

import io.github.shimeoki.jwp.app.actions.taglist.ListTagsHandler;
import io.github.shimeoki.jwp.app.actions.taglist.ListTagsQuery;
import io.github.shimeoki.jwp.cli.Command;
import io.github.shimeoki.jwp.cli.Runner;

public final class ListTagsRunner implements Runner {

    private final ListTagsHandler handler;

    public ListTagsRunner(final ListTagsHandler h) {
        handler = Objects.requireNonNull(h);
    }

    @Override
    public void run(Command cmd, String[] args) {
        if (args.length != 0) {
            throw new IllegalArgumentException("no args allowed");
        }

        final var res = handler.handle(new ListTagsQuery());

        System.out.println(String.join("\n", res.names()));
    }
}
