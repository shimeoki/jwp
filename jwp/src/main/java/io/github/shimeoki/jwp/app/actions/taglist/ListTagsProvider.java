package io.github.shimeoki.jwp.app.actions.taglist;

import io.github.shimeoki.jwp.app.Provider;
import io.github.shimeoki.jwp.domain.repositories.TagRepository;

public interface ListTagsProvider extends Provider {

    TagRepository tagRepository();
}
