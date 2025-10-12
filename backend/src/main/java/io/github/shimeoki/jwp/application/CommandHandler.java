package io.github.shimeoki.jwp.application;

public interface CommandHandler<C extends Command<R>, R> {

    R handle(C cmd);
}
