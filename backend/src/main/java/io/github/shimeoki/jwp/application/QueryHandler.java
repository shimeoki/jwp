package io.github.shimeoki.jwp.application;

public interface QueryHandler<Q extends Command<R>, R> {

    R handle(Q query);
}
