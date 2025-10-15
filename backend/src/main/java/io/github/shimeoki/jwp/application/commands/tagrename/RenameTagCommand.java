package io.github.shimeoki.jwp.application.commands.tagrename;

import io.github.shimeoki.jwp.application.Command;

public record RenameTagCommand(String before, String after)
        implements Command<RenameTagResult> {
}
