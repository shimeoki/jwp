package io.github.shimeoki.jwp;

import java.util.Objects;

import io.github.shimeoki.jwp.app.actions.tagcreate.CreateTagCommand;
import io.github.shimeoki.jwp.app.actions.tagdelete.DeleteTagCommand;
import io.github.shimeoki.jwp.app.actions.taglist.ListTagsQuery;
import io.github.shimeoki.jwp.app.actions.tagrename.RenameTagCommand;
import io.github.shimeoki.jwp.cli.Command;
import io.github.shimeoki.jwp.cli.Runner;
import io.github.shimeoki.jwp.cli.Runners;
import io.github.shimeoki.jwp.cli.runners.SessionRunner;
import io.github.shimeoki.jwp.config.App;
import io.github.shimeoki.jwp.config.Handlers;

public final class CLI implements Runner {

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

        command = new Command("jwp",
                (cmd, _) -> System.out.print(cmd.help()));

        command.addCommand(new Command("session",
                new SessionRunner(this)));

        addTagCommand();
    }

    private void addTagCommand() {
        final var tag = new Command(
                "tag",
                (cmd, _) -> System.out.println(cmd.help()));

        tag.addCommand(new Command(
                "create",
                Runners.exactArgs(1, (_, args) -> this.handlers.createTag()
                        .handle(new CreateTagCommand(args[0])))));

        tag.addCommand(new Command(
                "list",
                Runners.exactArgs(0, (_, _) -> System.out.println(
                        String.join("\n", this.handlers.listTags()
                                .handle(new ListTagsQuery()).names())))));

        tag.addCommand(new Command(
                "delete",
                Runners.exactArgs(1, (_, args) -> this.handlers.deleteTag()
                        .handle(new DeleteTagCommand(args[0])))));

        tag.addCommand(new Command(
                "rename",
                Runners.exactArgs(2, (_, args) -> this.handlers.renameTag()
                        .handle(new RenameTagCommand(args[0], args[1])))));

        command.addCommand(tag);
    }

    @Override
    public void run(Command cmd, String[] args) {
        command.execute(args);
    }
}
