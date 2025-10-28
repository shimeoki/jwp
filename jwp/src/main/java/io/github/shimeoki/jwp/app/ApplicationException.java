package io.github.shimeoki.jwp.app;

public class ApplicationException extends RuntimeException {

    public ApplicationException(final String action, final Throwable cause) {
        super(String.format("met an error in '%s' action: %s",
                action, cause.getMessage()), cause);
    }
}
