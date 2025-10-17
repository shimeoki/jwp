package io.github.shimeoki.jwp.cli;

@FunctionalInterface
public interface Runner {

    void run(Command cmd, String[] args);
}
