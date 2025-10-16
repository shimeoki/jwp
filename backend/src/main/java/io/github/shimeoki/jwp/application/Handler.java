package io.github.shimeoki.jwp.application;

public interface Handler<A, R> {

    R handle(A action);
}
