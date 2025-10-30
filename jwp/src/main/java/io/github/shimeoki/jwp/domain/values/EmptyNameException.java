package io.github.shimeoki.jwp.domain.values;

public class EmptyNameException extends RuntimeException {

    public EmptyNameException() {
        super("name is empty");
    }
}
