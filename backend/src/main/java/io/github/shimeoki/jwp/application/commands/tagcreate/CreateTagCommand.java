package io.github.shimeoki.jwp.application.commands.tagcreate;

import io.github.shimeoki.jwp.application.Command;

public record CreateTagCommand(String name)
        implements Command<CreateTagResult> {
}
