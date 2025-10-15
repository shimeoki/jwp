package io.github.shimeoki.jwp.application;

public interface QueryHandler<Q extends Query<R>, R> {

    R handle(Q qry);
}
