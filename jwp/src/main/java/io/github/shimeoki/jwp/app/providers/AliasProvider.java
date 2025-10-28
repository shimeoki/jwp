package io.github.shimeoki.jwp.app.providers;

import io.github.shimeoki.jwp.app.Provider;
import io.github.shimeoki.jwp.domain.repositories.AliasRepository;

public interface AliasProvider extends Provider {

    AliasRepository aliasRepository();
}
