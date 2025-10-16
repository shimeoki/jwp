package io.github.shimeoki.jwp.app;

@FunctionalInterface
public interface Handler<A, R> {

    R handle(A action);
}
