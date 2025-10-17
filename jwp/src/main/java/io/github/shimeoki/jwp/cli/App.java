package io.github.shimeoki.jwp.cli;

import io.github.shimeoki.jwp.cli.runners.SessionRunner;
import io.github.shimeoki.jwp.cli.runners.TagRunner;
import io.github.shimeoki.jwp.cli.runners.tag.CreateTagRunner;

public final class App {

    private static Command command;

    public static void main(String[] args) {
        init();
        command.execute(args);
    }

    private static void init() {
        if (command != null) {
            return;
        }

        command = new Command("jwp", (cmd, _) -> {
            System.out.print(cmd.help());
        });

        final var session = new Command("session", new SessionRunner());

        final var tag = new Command("tag", new TagRunner());
        final var tagCreate = new Command("create", new CreateTagRunner());

        tag.addCommand(tagCreate);

        command.addCommand(tag);
        command.addCommand(session);
    }
}
