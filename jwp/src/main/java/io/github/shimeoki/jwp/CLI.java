package io.github.shimeoki.jwp;

import java.util.Objects;
import java.util.Scanner;

import io.github.shimeoki.jwp.app.actions.tagcreate.CreateTagCommand;
import io.github.shimeoki.jwp.app.actions.tagdelete.DeleteTagCommand;
import io.github.shimeoki.jwp.app.actions.taglist.ListTagsQuery;
import io.github.shimeoki.jwp.app.actions.tagrename.RenameTagCommand;
import io.github.shimeoki.jwp.cli.Command;
import io.github.shimeoki.jwp.cli.Runner;
import io.github.shimeoki.jwp.cli.Runners;
import io.github.shimeoki.jwp.config.App;
import io.github.shimeoki.jwp.config.Handlers;

public final class CLI implements Runner {

    private static boolean session = false;

    private final App app;
    private final Command command;

    private Handlers handlers;

    // TODO: refactor into smaller functions
    public CLI(final App app) {
        this.app = Objects.requireNonNull(app);

        // ideally this can be done dynamically to change the config
        // from the commands (for example, to get --config command working),
        // but it works for now
        app.open();

        handlers = this.app.handlers();

        command = new Command(
                "jwp",
                "Manage your wallpapers in a hashed store",
                (cmd, _) -> System.out.print(cmd.help()));

        addTagCommand();
        addSessionCommand();
    }

    private void addTagCommand() {
        final var tag = new Command(
                "tag",
                "Tag related commands",
                (cmd, _) -> System.out.println(cmd.help()));

        tag.addCommand(new Command(
                "create [name]",
                "Create a tag with an unique name",
                Runners.exactArgs(1, (_, args) -> this.handlers.createTag()
                        .handle(new CreateTagCommand(args[0])))));

        tag.addCommand(new Command(
                "list",
                "Print all available tags",
                Runners.exactArgs(0, (_, _) -> System.out.println(
                        String.join("\n", this.handlers.listTags()
                                .handle(new ListTagsQuery()).names())))));

        tag.addCommand(new Command(
                "delete [name]",
                "Delete a tag with the specified name",
                Runners.exactArgs(1, (_, args) -> this.handlers.deleteTag()
                        .handle(new DeleteTagCommand(args[0])))));

        tag.addCommand(new Command(
                "rename [before] [after]",
                "Rename a tag: merge the 'before' tag with the 'after'",
                Runners.exactArgs(2, (_, args) -> this.handlers.renameTag()
                        .handle(new RenameTagCommand(args[0], args[1])))));

        command.addCommand(tag);
    }

    private void addSessionCommand() {
        command.addCommand(new Command(
                "session",
                "Enter a session to use other commands continuosly",
                Runners.exactArgs(0, (_, _) -> {
                    if (session) {
                        throw new IllegalStateException(
                                "recursive sessions are not allowed");
                    }

                    session = true;

                    try (var s = new Scanner(System.in)) {
                        System.out.print("> ");

                        while (s.hasNextLine()) {
                            final var line = s.nextLine();
                            final var opts = line.trim().split("\\s+");

                            try {
                                run(null, opts);
                            } catch (final Exception e) {
                                System.out.printf("error: %s\n", e.getMessage());
                            }

                            System.out.print("> ");
                        }
                    }

                    session = false;
                })));
    }

    @Override
    public void run(Command cmd, String[] args) {
        command.execute(args);
    }
}
