package io.github.shimeoki.jwp.application.commands.tagdelete;

import io.github.shimeoki.jwp.application.Command;

public record DeleteTagCommand(String name)
        implements Command<DeleteTagResult> {
}
