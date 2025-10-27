package io.github.shimeoki.jwp.app.providers;

import io.github.shimeoki.jwp.app.Provider;
import io.github.shimeoki.jwp.domain.repositories.SourceRepository;

public interface SourceProvider extends Provider {

    SourceRepository sourceRepository();
}
