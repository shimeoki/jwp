package io.github.shimeoki.jwp.application;

@FunctionalInterface
public interface Handler<A, R> {

    R handle(A action);
}
