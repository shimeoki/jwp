package io.github.shimeoki.jwp.app.actions.tagcreate;

import io.github.shimeoki.jwp.app.Provider;
import io.github.shimeoki.jwp.domain.repositories.TagRepository;

public interface CreateTagProvider extends Provider {

    TagRepository tagRepository();
}
