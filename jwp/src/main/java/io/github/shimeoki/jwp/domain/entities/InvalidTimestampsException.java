package io.github.shimeoki.jwp.domain.entities;

import java.util.Date;

public class InvalidTimestampsException extends RuntimeException {

    public InvalidTimestampsException(
            final Date createdAt,
            final Date updatedAt) {

        super(String.format("'created_at' is %s and 'updated_at' is %s ",
                createdAt.toString(), updatedAt.toString()));
    }
}
