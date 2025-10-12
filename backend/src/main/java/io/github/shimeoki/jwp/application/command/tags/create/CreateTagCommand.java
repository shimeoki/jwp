package io.github.shimeoki.jwp.application.command.tags.create;

import io.github.shimeoki.jwp.application.Command;

public record CreateTagCommand(String name)
        implements Command<CreateTagResult> {
}
