package io.github.shimeoki.jwp.app;

@FunctionalInterface
public interface Worker<P extends Provider> {

    P work();
}
