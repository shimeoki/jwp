package io.github.shimeoki.jwp.application.command.tags.delete;

import io.github.shimeoki.jwp.application.Command;

public record DeleteTagCommand(String name)
        implements Command<DeleteTagResult> {
}
