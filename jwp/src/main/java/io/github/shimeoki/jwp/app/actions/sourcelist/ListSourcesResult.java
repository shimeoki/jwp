package io.github.shimeoki.jwp.app.actions.sourcelist;

import java.util.Collection;

public record ListSourcesResult(Collection<Source> sources) {

    public record Source(String id, String name, String link) {
    }
}
