package io.github.shimeoki.jwp.cli.runners;

import io.github.shimeoki.jwp.cli.Command;
import io.github.shimeoki.jwp.cli.Runner;

public final class TagRunner implements Runner {

    @Override
    public void run(final Command cmd, final String[] args) {
        System.out.print(cmd.help());
    }
}
