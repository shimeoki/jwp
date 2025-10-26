package io.github.shimeoki.jwp.app.providers;

import io.github.shimeoki.jwp.app.Provider;
import io.github.shimeoki.jwp.domain.repositories.Store;

public interface StoreProvider extends Provider {

    Store store();
}
